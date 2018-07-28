package com.etherbet.job;

import com.etherbet.dto.Fixture;
import com.etherbet.ipfs.service.IpfsService;
import com.etherbet.util.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@Component
public class EtherScheduler {

    public static final Logger LOGGER = LoggerFactory.getLogger(EtherScheduler.class);

    @Autowired
    public IpfsService ipfsService;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public ObjectMapper objectMapper;

    @Scheduled(cron = "${cron.expression}")
    public void pushFixtureToIPFS() {
        long startTime = Calendar.getInstance().getTimeInMillis();

        LOGGER.info("Starting push fixture to IPFS... ", Calendar.getInstance().getTime());
        final Fixture fixture = prepareFixtureForTest();
        try {
            Map<Integer, String> indexHash = EtherJob.process(ipfsService, fixture);
            var json = objectMapper.writeValueAsString(indexHash);
            final String appDir = System.getProperty("vn.fb.appDir");
            FileUtils.write(json, appDir + "/data/indexHash.json");
        } catch (IOException e) {
            LOGGER.error("Error occurs when push data to IPFS.", e);
        }

        long endtime = Calendar.getInstance().getTimeInMillis();
        LOGGER.info("Finish push fixture to IPFS in {} millis ", endtime - startTime);
    }

    private Fixture prepareFixtureForTest() {
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get("/Users/KAI/PROJECT/JAVA/etherbet/src/main/resources/staticData.json"));
            ObjectMapper mapper = new ObjectMapper();
            Fixture fixture = mapper.readValue(bytes, Fixture.class);
            return fixture;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
