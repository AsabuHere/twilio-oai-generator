package com.twilio.oai.resource;

import com.google.common.collect.Streams;
import com.twilio.oai.Inflector;
import com.twilio.oai.PathUtils;
import com.twilio.oai.TwilioJavaGenerator;
import io.swagger.v3.oas.models.PathItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class Resource {
    @Getter @Setter(AccessLevel.PROTECTED) private String path;
    @Getter @Setter(AccessLevel.PROTECTED) private List<Resource> childComponents = new ArrayList<>();
    @Getter @Setter(AccessLevel.PROTECTED) private PathItem pathItem;
    private Inflector inflector = new Inflector();

    public Resource(String name, PathItem pathItem) {
        path = name;
        this.pathItem = pathItem;
    }

    public Resource getParentResource(ResourceTree resourceTree) {
        for(Map.Entry entrySet: pathItem.getExtensions().entrySet()) {
            if (entrySet.getKey().equals("x-twilio")) {
                if (((Map<?, ?>) entrySet.getValue()).containsKey("parent")) {
                    return resourceTree.findResource(Arrays.stream(PathUtils.cleanPath(((Map<?, String>) entrySet.getValue()).get("parent")).split("/")).filter(item-> !item.isEmpty()).collect(Collectors.joining(TwilioJavaGenerator.PATH_SEPARATOR_PLACEHOLDER)));
                }
            }
        }
        return null;
    }

    public String getClassName() {
        for(Map.Entry entrySet: pathItem.getExtensions().entrySet()) {
            if (entrySet.getKey().equals("x-twilio")) {
                if (((Map<?, ?>) entrySet.getValue()).containsKey("className")) {
                    return Arrays.stream(((Map<?, String>) entrySet.getValue()).get("className").split("_"))
                            .map(TwilioJavaGenerator::capitalize).collect(Collectors.joining());
                }
            }
        }
        return Streams.findLast(Arrays.stream(path.split(TwilioJavaGenerator.PATH_SEPARATOR_PLACEHOLDER))).get();
    }

    public Resource addChildComponent(Resource resource) {
        this.childComponents.add(resource);
        return this;
    }

    public Boolean isChildComponent(String childName) {
        for(Resource resource: childComponents) {
            if(resource.path.toUpperCase(Locale.ROOT).equals(childName.toUpperCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    public String resourceName() {
        return this.path;
    }

    public Resource childComponent(String resourceSubString) {
        for( Resource resource : childComponents) {
            if (resource.path.toUpperCase(Locale.ROOT).equals(resourceSubString.toUpperCase(Locale.ROOT))) {
                return resource;
            }
        }
        return null;
    }


    private String mapPackageVersion(final String version) {
        if (version.equals("2010-04-01")) {
            return "v2010";
        }
        return version;
    }


}
