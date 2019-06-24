package com.view.points.mostviews.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.File;
import java.io.IOException;

public class JsonHelper {
    public static <T> T readObject(String path, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper().configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true);
        mapper.registerModule(new Jdk8Module());
        File file = new File(clazz.getClassLoader().getResource(path).getFile());
        T t;
        try {
            t = mapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    public static <T> void writeObject(String path, T t) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        File file = new File(path);
        try {
            mapper.writeValue(file, t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
