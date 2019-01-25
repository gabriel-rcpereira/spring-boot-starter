package com.example.springbootstarter.api.service;

import com.example.springbootstarter.api.model.response.LetterResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonService {

    private ObjectMapper objectMapper;

    public JsonService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(Object clazz) throws IOException {
        return objectMapper.writeValueAsString(clazz);
    }

    public <T extends Object> T fromJson(String json, T clazz) throws IOException {
        return objectMapper.readValue(json, (Class<T>) clazz);
    }

    public <T extends Object> List<T> fromJsonToList(String json, Class<T> clazz) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);

        return objectMapper.readValue(json, javaType);
    }
}
