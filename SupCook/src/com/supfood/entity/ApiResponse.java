package com.supfood.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.supfood.api.client.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiResponse {
    private boolean success;
    private String content;

    public ApiResponse(boolean success, String content) {
        this.success = success;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getContent() {
        return content;
    }

    public static String getAsJson(boolean success, String content) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", success);
        jsonResponse.addProperty("content", content);
        Gson gson = new Gson();
        return gson.toJson(jsonResponse);
    }

    public void redirectWithError(HttpServletResponse response, String redirectUrl) throws IOException {
        Utils.redirectWithError(response, redirectUrl, this.getContent());
    }
}
