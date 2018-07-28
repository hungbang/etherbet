package com.etherbet.service;

import com.etherbet.dto.Match;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@Component
public class FileService {

    @Autowired
    public ObjectMapper objectMapper;

    public Map<Integer, String> getIndexHash() throws IOException {
        final String appDir = System.getProperty("vn.fb.appDir");
        File file = new File(appDir + "/data/indexHash.json");
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, Integer.class, String.class);
        Map<Integer, String> indexHash = objectMapper.readValue(file, javaType);
        return indexHash;
    }

}
