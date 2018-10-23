package com.supfood.api.client;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.supfood.api.gson.GsonUtils;
import com.supfood.entity.ApiResponse;

import javax.ws.rs.core.MediaType;

public class ClientApi {
    static String API_URL = "http://localhost:8080/api/";
    static String API_TOKEN = "1367d318-0c30-11e8-ba89-0ed5f89f718b";

    public static WebResource.Builder getResource(String resourceUrl) {
        Client client = Client.create();
        String url = API_URL + resourceUrl;
        WebResource resource = client.resource(url);
        return resource.header("token", API_TOKEN);
    }

    public static ApiResponse jsonToApiResponse(String jsonApiResponse) {
        Gson gson = new Gson();
        return gson.fromJson(jsonApiResponse, ApiResponse.class);
    }

    public static ApiResponse createObject(Object object, String apiSuffix) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        String res = getResource(apiSuffix + "/auth")
                .type(MediaType.APPLICATION_JSON)
                .post(String.class, json);
        return jsonToApiResponse(res);
    }

    public static ApiResponse getAllObject(String apiSuffix) {
        return getRequest(apiSuffix);
    }

    public static Object getObject(int id, String apiSuffix, Class objClass) {
        String requestUrl = apiSuffix + "/" + id;
        ApiResponse res = getRequest(requestUrl);
        Gson gson = new Gson();
        if (res.isSuccess()) {
            return gson.fromJson(res.getContent(), objClass);
        }
        return res;
    }

    public static ApiResponse updateObject(Object object, String apiSuffix) {
        Gson gson = GsonUtils.getGsonBuilder();
        String json = gson.toJson(object);
        String jsonRes = getResource(apiSuffix + "/auth")
                .type(MediaType.APPLICATION_JSON)
                .put(String.class, json);
        return jsonToApiResponse(jsonRes);
    }

    public static ApiResponse removeObject(int id, String apiSuffix) {
        String requestUrl = apiSuffix + "/auth/" + id;
        String jsonRes = getResource(requestUrl)
                .delete(String.class);
        return jsonToApiResponse(jsonRes);
    }

    public static ApiResponse callHandler(String resource, String jsonData) {
        String res = getResource(resource)
                .type(MediaType.APPLICATION_JSON)
                .post(String.class, jsonData);
        return jsonToApiResponse(res);
    }

    public static ApiResponse getRequest(String resource) {
        String res = getResource(resource)
                .get(String.class);
        return jsonToApiResponse(res);
    }
}
