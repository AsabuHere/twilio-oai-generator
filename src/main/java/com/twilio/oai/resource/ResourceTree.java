package com.twilio.oai.resource;

import com.twilio.oai.Inflector;
import com.twilio.oai.PathUtils;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.openapitools.codegen.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResourceTree implements IResourceTree {
    private Paths paths;
    private Resource root;
    private String pathDelimiter;
    private Inflector inflector = new Inflector();

    public ResourceTree(Paths pathItems, String version, String pathDelimiter) {
        this.paths = pathItems;
        this.root = new Resource(version, null);
        this.pathDelimiter = pathDelimiter;
        createTree();
    }


    @Override
    public List<String> ancestors(String resourceName) {
        List<String> ancestors = new ArrayList<>();
        Resource resource = this.findResource(resourceName);
        String className = StringUtils.camelize(inflector.singular(resource.getClassName()));
        while (true) {
            Resource parentResource = resource.getParentResource(this);
            if ((parentResource == null)) break;
            ancestors.add(0, inflector.singular(parentResource.getClassName().toLowerCase(Locale.ROOT)));
            resource = parentResource;
        }
        ancestors.add(className);
        return ancestors;
    }

    @Override
    public Resource findResource(String name) {
        Resource rootTemp = this.root;
        String[] resourceStringArray = name.split(pathDelimiter);
        for (String resourceString: resourceStringArray) {
            if (!rootTemp.isChildComponent(resourceString)) {
                return null;
            } rootTemp = rootTemp.childComponent(resourceString);
        }
        return rootTemp;
    }

    private void createTree() {
        paths.forEach((name, pathItem) -> {
            name = PathUtils.cleanPath(name).replace("/", pathDelimiter);
            if (!this.contains(name)) {
                this.addResource(name, pathItem);
            }
        });
    }

    private Resource addResource(String name, PathItem pathItem) {
        Resource rootTemp = this.root;
        String[] resourceStringArray = name.split(pathDelimiter);
        for (String resourceString: resourceStringArray) {
            if (!rootTemp.isChildComponent(resourceString)) {
                rootTemp.addChildComponent(new Resource(resourceString, pathItem));
            }
            rootTemp = rootTemp.childComponent(resourceString);
        }
        return  rootTemp;
    }

    private Boolean contains(String name) {
        Resource resource = root;
        String[] resourceStringArray = name.split(pathDelimiter);
        for (String resourceSubString : resourceStringArray) {
            if (resource.isChildComponent(resourceSubString)) {
                resource = resource.childComponent(resourceSubString);
            } else {
                return false;
            }
        }
        return  true;
    }
}
