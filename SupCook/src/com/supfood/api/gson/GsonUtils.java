package com.supfood.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {

    public static Gson getGsonBuilder() {
        return new GsonBuilder()
                .setExclusionStrategies(new ListExclusionStrategy())
                .create();
    }
}
