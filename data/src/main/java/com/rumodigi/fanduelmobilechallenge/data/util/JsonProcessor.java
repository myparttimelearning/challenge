package com.rumodigi.fanduelmobilechallenge.data.util;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JsonProcessor{

private final Gson gson = new Gson();

@Inject
JsonProcessor(){}

/**
 *
 * Convert an object to json
 *
 * @param object to convert
 *
 */

public String convertToJson(Object object, Class out){
    return gson.toJson(object, out);
}

    /**
     *
     * Convert an object from json
     *
     * @param json to convert
     *
     */

public <T> T convertFromJson(String json, Class<T> out){
    return gson.fromJson(json, out);
}

}
