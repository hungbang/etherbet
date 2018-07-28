package com.etherbet.controller;

import com.etherbet.dto.Fixture;
import com.etherbet.dto.Match;
import com.etherbet.ipfs.service.IpfsService;
import com.etherbet.service.FileService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@Controller
@RequestMapping("api/fixtures")
public class FixtureController {

    @Autowired
    public IpfsService ipfsService;

    @Autowired
    public FileService fileService;

    @GetMapping("indexHash")
    public ResponseEntity getAllIndexHash() throws IOException {
        Map<Integer, String> indexHash = fileService.getIndexHash();
        return ResponseEntity.ok(indexHash);
    }
}
