package com.twilio.oai.api;

import com.twilio.oai.DirectoryStructureService;
import com.twilio.oai.resolver.ISchemaResolver;
import com.twilio.oai.resolver.Resolver;
import org.openapitools.codegen.CodegenModel;
import org.openapitools.codegen.CodegenParameter;
import org.openapitools.codegen.CodegenProperty;

public interface IApiResourceBuilder {
    IApiResourceBuilder updateTemplate();
    IApiResourceBuilder updateOperations(ISchemaResolver<CodegenParameter> codegenParameterIResolver);
    IApiResourceBuilder updateResponseModel(ISchemaResolver<CodegenProperty> codegenPropertyIResolver);

    default IApiResourceBuilder updateResponseModel(ISchemaResolver<CodegenProperty> codegenPropertyIResolver, Resolver<CodegenModel> codegenModelResolver){
        return updateResponseModel( codegenPropertyIResolver);
    }
    IApiResourceBuilder updateAdditionalProps(DirectoryStructureService directoryStructureService);
    IApiResourceBuilder updateApiPath();
    IApiResourceBuilder setImports(DirectoryStructureService directoryStructureService);
    <T extends ApiResources> T build();
}