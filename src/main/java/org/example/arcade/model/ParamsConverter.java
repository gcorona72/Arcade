// src/main/java/org/example/arcade/model/ParamsConverter.java
package org.example.arcade.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import java.util.Collections;
import java.util.Map;

public class ParamsConverter implements AttributeConverter<Map<String,Object>, String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String,Object> params) {
        try {
            return mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    @Override
    public Map<String,Object> convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, Map.class);
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }
}