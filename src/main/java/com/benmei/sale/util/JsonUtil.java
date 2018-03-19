package com.benmei.sale.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Micoo on 2017/5/9.
 */
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        String json = "{\"toJsonError\":\"" + obj + "\"}";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static <T> T toObject(String resp, Class<T> c) throws IOException {
        return mapper.readValue(resp, c);
    }
}
