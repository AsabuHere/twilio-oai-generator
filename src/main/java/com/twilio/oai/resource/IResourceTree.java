package com.twilio.oai.resource;

import java.util.List;

public interface IResourceTree {
     List<String> ancestors(String resourceName);
     Resource findResource(String name);
}
