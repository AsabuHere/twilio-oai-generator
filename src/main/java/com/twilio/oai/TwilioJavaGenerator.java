package com.twilio.oai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import lombok.AllArgsConstructor;
import org.openapitools.codegen.*;
import org.openapitools.codegen.languages.JavaClientCodegen;
import org.openapitools.codegen.utils.StringUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TwilioJavaGenerator extends JavaClientCodegen {

    // Unique string devoid of symbols.
    private static final String PATH_SEPARATOR_PLACEHOLDER = "1234567890";
    private static final int OVERFLOW_CHECKER = 32;
    private static final int BASE_SIXTEEN = 16;
    private static final int BIG_INTEGER_CONSTANT = 1;
    private static final int SERIAL_UID_LENGTH = 12;

    private final List<CodegenModel> allModels = new ArrayList<>();
    private  Map<String, String> modelFormatMap = new HashMap<>();
    private Map<String, String> apiNameMap = new HashMap<>();
    private final Inflector inflector = new Inflector();

    public TwilioJavaGenerator() {
        super();

        // Remove the "API" suffix from the API filenames.
        apiNameSuffix = "";

        // Find the templates in the local resources dir.
        embeddedTemplateDir = templateDir = getName();
        sourceFolder = "";

        // Skip automated api test and doc generation
        apiTestTemplateFiles.clear();
        apiDocTemplateFiles.clear();
    }

    @Override
    public void processOpts() {
        super.processOpts();

        final String inputSpecPattern = ".+_(?<domain>.+)_(?<version>.+)\\..+";
        final String version = inputSpec.replaceAll(inputSpecPattern, "${version}");
        final String domain = inputSpec.replaceAll(inputSpecPattern, "${domain}");
        apiPackage = version; // Place the API files in the version folder.
        additionalProperties.put("apiVersion", version);
        additionalProperties.put("apiVersionClass", version.toUpperCase());
        additionalProperties.put("domain", StringUtils.camelize(domain));
        additionalProperties.put("domainPackage", domain.toLowerCase());

        supportingFiles.clear();
        apiTemplateFiles.put("api.mustache", ".java");
        apiTemplateFiles.put("creator.mustache", "Creator.java");
        apiTemplateFiles.put("deleter.mustache", "Deleter.java");
        apiTemplateFiles.put("fetcher.mustache", "Fetcher.java");
        apiTemplateFiles.put("reader.mustache", "Reader.java");
        apiTemplateFiles.put("updater.mustache", "Updater.java");

        supportingFiles.add(new SupportingFile("Domains.mustache", "Domains.java"));
    }

    @Override
    public void processOpenAPI(final OpenAPI openAPI) {
        List<String> paths = new ArrayList<>();
        openAPI.getPaths().forEach((name, path) -> {
            final String tag = PathUtils.cleanPath(name).replace("/", PATH_SEPARATOR_PLACEHOLDER);
            paths.add(tag);
        });
        openAPI.getPaths().forEach((name, path) -> {
            final String tag = PathUtils.cleanPath(name).replace("/", PATH_SEPARATOR_PLACEHOLDER);
            createAPIPathMap(tag, path, paths);
            path.readOperations().forEach(operation -> {
                // Group operations together by tag. This gives us one file/post-process per resource.
                operation.addTagsItem(tag);
            });
        });
        System.out.println(apiNameMap);
    }

    @Override
    public String toParamName(String name) {
        name = name.replace("<", "Before");
        name = name.replace(">", "After");
        name = super.toVarName(name);
        return name;
    }


    @Override
    public void postProcessParameter(final CodegenParameter parameter) {
        super.postProcessParameter(parameter);

        // Make sure required non-path params get into the options block.
        parameter.paramName = StringUtils.camelize(parameter.paramName, false);
    }

    @Override
    public String toApiFilename(final String name) {
        return getPackagePath(super.toApiFilename(name));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> postProcessAllModels(final Map<String, Object> allModels) {
        final Map<String, Object> results = super.postProcessAllModels(allModels);

        for (final Object obj : results.values()) {
            final Map<String, Object> mods = (Map<String, Object>) obj;
            final ArrayList<Map<String, Object>> modList = (ArrayList<Map<String, Object>>) mods.get("models");

            // Add all the models to the local models list.
            modList
                    .stream()
                    .map(model -> model.get("model"))
                    .map(CodegenModel.class::cast)
                    .collect(Collectors.toCollection(() -> this.allModels));
        }
        setObjectFormatMap(this.allModels);
        // Return an empty collection so no model files get generated.
        return new HashMap<>();
    }


    @Override
    public Map<String, Object> postProcessOperationsWithModels(final Map<String, Object> objs,
                                                               final List<Object> allModels) {
        final Map<String, Object> results = super.postProcessOperationsWithModels(objs, allModels);

        final Map<String, Map<String, Object>> resources = new LinkedHashMap<>();

        final Map<String, Object> ops = getStringMap(results, "operations");
        final ArrayList<CodegenOperation> opList = (ArrayList<CodegenOperation>) ops.get("operation");
        String recordKey = getRecordKey(opList, this.allModels);
        List<CodegenModel> responseModels = new ArrayList<CodegenModel>();
        // iterate over the operation and perhaps modify something
        for (final CodegenOperation co : opList) {
            // Group operations by resource.
            String path = co.path;
            String resourceName = inflector.singular(TwilioJavaGenerator.capitalize(apiNameMap.get(co.baseName.toUpperCase())));
            final Map<String, Object> resource = resources.computeIfAbsent(resourceName, k -> new LinkedHashMap<>());
            populateCrudOperations(resource, co);
            if (co.path.endsWith("}") || co.path.endsWith("}.json")) {
                if ("POST".equalsIgnoreCase(co.httpMethod)) {
                    resource.put("hasUpdate", true);
                    addOperationName(co, "Update");
                    co.vendorExtensions.put("x-is-update-operation", true);
                    resource.put("requiredParamsUpdate", co.requiredParams);
                } else if ("DELETE".equalsIgnoreCase(co.httpMethod)) {
                    resource.put("hasDelete", true);
                    addOperationName(co, "Remove");
                    co.vendorExtensions.put("x-is-delete-operation", true);
                    resource.put("requiredParamsDelete", co.requiredParams);
                }
            } else {
                if ("POST".equalsIgnoreCase(co.httpMethod)) {
                    resource.put("hasCreate", true);
                    co.vendorExtensions.put("x-is-create-operation", true);
                    addOperationName(co, "Create");
                    resource.put("requiredParamsCreate", co.requiredParams);
                }
            }

            if (!co.nickname.startsWith("list")) {
                if ("GET".equalsIgnoreCase(co.httpMethod)) {
                    resource.put("hasFetch", true);
                    resource.put("requiredParamsFetch", co.requiredParams);
                    co.vendorExtensions.put("x-is-fetch-operation", true);
                    addOperationName(co, "Fetch");
                }
            } else {
                if ("GET".equalsIgnoreCase(co.httpMethod)) {
                    resource.put("hasRead", true);
                    co.vendorExtensions.put("x-is-read-operation", true);
                    addOperationName(co, "Page");
                    resource.put("requiredParamsRead", co.requiredParams);
                }
            }

            final ArrayList<CodegenOperation> resourceOperationList =
                    (ArrayList<CodegenOperation>) resource.computeIfAbsent(
                            "operations",
                            k -> new ArrayList<>());
            resourceOperationList.add(co);
            resource.put("path", path);
            resource.put("resourceName", resourceName);
            resource.put("resourcePathParams", co.pathParams);
            resource.put("resourceRequiredParams", co.requiredParams);
            co.queryParams =  co.queryParams.stream().map(ConventionResolver::resolveParamTypes).map(ConventionResolver::prefixedCollapsibleMap).collect(Collectors.toList());
            co.pathParams = null;
            co.hasParams = !co.allParams.isEmpty();
            co.allParams = co.allParams.stream().map(ConventionResolver::resolveParamTypes).collect(Collectors.toList());
            co.hasRequiredParams = !co.requiredParams.isEmpty();

            if (co.bodyParam != null) {
                addModel(resource, co.bodyParam.dataType);
            }
            co.responses
              .stream()
              .map(response -> response.dataType)
              .filter(Objects::nonNull)
              .map(modelName -> this.getModelCoPath(modelName, co, recordKey))
              .map(ConventionResolver::resolve)
              .map(item -> ConventionResolver.resolveComplexType(item, modelFormatMap))
              .flatMap(Optional::stream)
              .forEach(model -> {
                  responseModels.add(model);
                  resource.put("serialVersionUID", calculateSerialVersionUid(model.vars));

              });
            results.put("recordKey", getRecordKey(opList, this.allModels));
            results.put("packageName", getPackageName(co.path));
            List<String> packagePaths = Arrays.stream(getPackagePath(co.baseName).split("/")).collect(Collectors.toList());
            String packagePath = packagePaths.subList(0,packagePaths.size()-1).stream().collect(Collectors.joining("."));
            if (packagePath.isEmpty()) {
                resource.put("packageSubPart", packagePath);
            } else {
                resource.put("packageSubPart", "."+packagePath);
            }
        }

        for (final Map<String, Object> resource : resources.values()) {
            resource.put("responseModel", getConcatenatedResponseModel(responseModels));
            flattenStringMap(resource, "models");
        }

        results.put("resources", resources.values());

        return results;
    }

    private CodegenModel getConcatenatedResponseModel(List<CodegenModel> responseModels) {
        CodegenModel codegenModel = new CodegenModel();
        codegenModel.allowableValues = new HashMap<>();
        List<CodegenProperty> codegenProperties = new ArrayList<>();
        for (CodegenModel resModel : responseModels) {
                codegenModel.hasEnums = codegenModel.hasEnums || resModel.hasEnums;
                codegenModel.isEnum = codegenModel.isEnum || resModel.isEnum;
                if (resModel.allowableValues != null) {
                    resModel.allowableValues.forEach(
                            (key, value) -> codegenModel.allowableValues.merge(key, value, (oldValue, newValue) -> newValue));
                }
                for (CodegenProperty modelProp : resModel.vars) {
                        boolean contains = false;
                        for (CodegenProperty property : codegenProperties) {
                                if (property.baseName.equals(modelProp.baseName)) {
                                        contains = true;
                                    }
                            }
                        if (!contains) {
                                codegenProperties.add(modelProp);
                            }
                    }
            }
        codegenModel.setVars(codegenProperties);
        return codegenModel;
    }

    private Optional<CodegenModel> getModelCoPath(final String modelName, CodegenOperation codegenOperation, String recordKey) {
        if (codegenOperation.vendorExtensions.containsKey("x-is-read-operation") && (boolean)codegenOperation.vendorExtensions.get("x-is-read-operation")) {
                Optional<CodegenModel> coModel = allModels.stream().filter(model -> model.getClassname().equals(modelName)).findFirst();
                if (!coModel.isPresent()) {
                        return Optional.empty();
                    }
                CodegenProperty property = coModel.get().vars.stream().filter(prop -> prop.baseName.equals(recordKey)).findFirst().get();
                Optional<CodegenModel> complexModel = allModels.stream().filter(model -> model.getClassname().equals(property.complexType)).findFirst();
                return complexModel;
            }
        return allModels.stream().filter(model -> model.getClassname().equals(modelName)).findFirst();
    }

    private String getRecordKey(List<CodegenOperation> opList, List<CodegenModel> models) {
        String recordKey =  "";
        for (CodegenOperation co: opList) {
            for(CodegenModel model: models) {
                if(model.name.equals(co.returnType)) {
                    recordKey = model.allVars
                            .stream()
                            .filter(v -> v.openApiType.equals("array"))
                            .collect(Collectors.toList()).get(0).baseName;
                }
            }
        }
        return recordKey;
    }

    @AllArgsConstructor
    private enum Operation {
        CREATE("create"),
        FETCH("fetch"),
        UPDATE("update"),
        DELETE("delete");

        private final String prefix;
    }

    private void populateCrudOperations(final Map<String, Object> resource, final CodegenOperation operation) {
        if (operation.nickname.startsWith(Operation.CREATE.prefix)) {
            operation.vendorExtensions.put("x-is-create-operation", true);
            resource.put(Operation.CREATE.name(), operation);
        } else if (operation.nickname.startsWith(Operation.FETCH.prefix)) {
            operation.vendorExtensions.put("x-is-fetch-operation", true);
            resource.put(Operation.FETCH.name(), operation);
        } else if (operation.nickname.startsWith(Operation.UPDATE.prefix)) {
            operation.vendorExtensions.put("x-is-update-operation", true);
            resource.put(Operation.UPDATE.name(), operation);
        } else if (operation.nickname.startsWith(Operation.DELETE.prefix)) {
            operation.vendorExtensions.put("x-is-delete-operation", true);
            resource.put(Operation.DELETE.name(), operation);
        }
    }

    private void addModel(final Map<String, Object> resource, final String dataType) {
        getModel(dataType).ifPresent(model -> {
            if (getStringMap(resource, "models").putIfAbsent(model.getClassname(), model) == null) {
                model.getVars().forEach(property -> addModel(resource, property.dataType));
            }
        });
    }

    private Optional<CodegenModel> getModel(final String modelName) {
        return allModels.stream().filter(model -> model.getClassname().equals(modelName)).findFirst();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getStringMap(final Map<String, Object> resource, final String key) {
        return (Map<String, Object>) resource.computeIfAbsent(key, k -> new HashMap<>());
    }

    @SuppressWarnings("unchecked")
    private void flattenStringMap(final Map<String, Object> resource, final String key) {
        resource.computeIfPresent(key, (k, dependents) -> ((Map<String, Object>) dependents).values());
    }

    private String getPackagePath(final String pathBaseName) {
        String pathBaseNameUpper = pathBaseName.toUpperCase();
        List<String> apiPathList = new ArrayList<>();
        List<String> elements =  Arrays.stream(pathBaseNameUpper.split(PATH_SEPARATOR_PLACEHOLDER))
                .collect(Collectors.toList());
        if (pathBaseNameUpper.equals("SETTINGS")) {
            apiPathList.add("dialingpermission");
            apiPathList.add("Setting");
        } else {
            for (int i = 0; i < elements.size(); i++) {
                if (i == 0 && elements.size() == 1) {
                    apiPathList.add(TwilioJavaGenerator.capitalize(inflector.singular(apiNameMap.get(elements.get(i)))));
                } else {
                    String key = elements.subList(0, i + 1).stream().collect(Collectors.joining(PATH_SEPARATOR_PLACEHOLDER)).toUpperCase();
                    if (!apiNameMap.containsKey(key)) {
                        continue;
                    }
                    String value = apiNameMap.get(key);
                    if (i < elements.size() - 1) {
                        apiPathList.add(inflector.singular(value.toLowerCase(Locale.ROOT)));
                    } else {
                        apiPathList.add(StringUtils.camelize(inflector.singular(value), false));
                    }
                }
            }
        }
        return apiPathList.stream().collect(Collectors.joining(File.separator));
    }

    private String getPackageName(final String path) {
        return Arrays
                .stream(PathUtils.cleanPath(path).split("/"))
                .map(this::singular)
                .map(String::toLowerCase)
                .map(this::mapPackageVersion)
                .collect(Collectors.joining("."));
    }

    private String mapPackageVersion(final String version) {
        if (version.equals("2010-04-01")) {
            return "v2010";
        }
        return version;
    }

    private String singular(final String plural) {
        return (inflector.singular(plural));
    }

    private void createAPIPathMap(final String path, final PathItem pathMap, List<String> paths) {
        String[] elements = path.split(PATH_SEPARATOR_PLACEHOLDER);
        apiNameMap.put(path.toUpperCase(), TwilioJavaGenerator.capitalize(elements[elements.length-1]));
        if (pathMap.getExtensions() != null) {
            pathMap.getExtensions().forEach((key, value) -> {
                if (key.equals("x-twilio")) {
                    if(((Map<?, ?>) value).containsKey("className")) {
                        String fileName = Arrays.stream(((Map<?, String>) value).get("className").split("_")).map(TwilioJavaGenerator::capitalize).collect(Collectors.joining());
                        apiNameMap.put(path.toUpperCase(), fileName);
                    }
                    if(((Map<?, ?>) value).containsKey("parent")) {
                        String dirName = Arrays.stream(((Map<?, String>) value).get("parent").split("_")).collect(Collectors.joining());
                        int index = 1;
                        String apiKey = Arrays.stream(Arrays.copyOfRange(elements, 0, elements.length - index)).collect(Collectors.joining(PATH_SEPARATOR_PLACEHOLDER));
                        if (path.equals("Settings")) {
                            apiKey = "DIALINGPERMISSIONS"+PATH_SEPARATOR_PLACEHOLDER+"SETTINGS";
                        } else {
                            while (!paths.contains(apiKey)) {
                                index += 1;
                                apiKey = Arrays.stream(Arrays.copyOfRange(elements, 0, elements.length - index)).collect(Collectors.joining(PATH_SEPARATOR_PLACEHOLDER));
                            }
                        }
                        apiNameMap.put(apiKey.toUpperCase(), dirName.toLowerCase(Locale.ROOT));
                    }
                }
            });
        }
    }

    private void addOperationName(final CodegenOperation operation, final String name) {
        operation.vendorExtensions.put("x-name", name);
        operation.vendorExtensions.put("x-name-lower", name.toLowerCase());
    }

    private long calculateSerialVersionUid(final List<CodegenProperty> modelProperties){

        String signature = calculateSignature(modelProperties);
        return Long.parseLong(getMd5(signature).substring(0,SERIAL_UID_LENGTH), BASE_SIXTEEN);
    }

    private String calculateSignature(final List<CodegenProperty> modelProperties){

        Map<String, String> propertyMap = new HashMap<>();
        for(CodegenProperty property : modelProperties){
            String key = property.name;
            String type = property.dataType; //concatenate the class name
            propertyMap.put(key,type);
        }

        ArrayList<String> sortedKeys = new ArrayList<String>(propertyMap.keySet());
        Collections.sort(sortedKeys);
        StringBuilder sb = new StringBuilder();
        for (String key : sortedKeys){
             sb.append("|");
             sb.append(key.toLowerCase());
             sb.append("|");
             sb.append(propertyMap.get(key).toLowerCase());
        }
        return sb.toString();

    }

    private String getMd5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger bigInteger = new BigInteger(BIG_INTEGER_CONSTANT, messageDigest);
            String hashtext = bigInteger.toString(BASE_SIXTEEN);
            while (hashtext.length() < OVERFLOW_CHECKER) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void setObjectFormatMap(final List<CodegenModel> allModels) {
        allModels.forEach(item -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(item.modelJson);
                if (jsonNode.get("type").textValue().equals("object") && jsonNode.has("format")) {
                    modelFormatMap.put(item.classFilename, jsonNode.get("format").textValue());
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public String getName() {
        return "twilio-java";
    }

    @Override
    public String getHelp() {
        return "Generates the twilio-java helper library.";
    }
}