package com.etherbet.job;

import com.etherbet.dto.Fixture;
import com.etherbet.ipfs.service.IpfsService;
import com.etherbet.service.SenderEmailService;
import com.etherbet.util.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.fxml.builder.URLBuilder;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

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

    @Value("${england.premier.league.api}")
    public String premierApi;
    @Value("${header.custom.token}")
    public String customHeaderToken;
    @Value("${x.auth.token}")
    public String tokenValue;

    @Autowired
    public SenderEmailService senderEmailService;

    @Scheduled(cron = "${cron.expression}")
    public void pushFixtureToIPFS() {
        long startTime = Calendar.getInstance().getTimeInMillis();

        LOGGER.info("Starting push fixture to IPFS... ", Calendar.getInstance().getTime());
        final Fixture fixture = getFixtureFromApi();
        if (Objects.isNull(fixture)) {
            LOGGER.error("Can not get fixture from api {}.", premierApi);
        }
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
        senderEmailService.sendEmailAfterAPIScheduleJob(premierApi, startTime, endtime, endtime - startTime);
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

    public Fixture getFixtureFromApi() {
        LOGGER.info("Fetching data from api {} ", premierApi);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(premierApi).build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(customHeaderToken, tokenValue);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Fixture> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, Fixture.class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            LOGGER.info("Finish data from api {} ", premierApi);
            return response.getBody();

        }
        LOGGER.info("Error when get data from api {} ", premierApi);
        return null;
    }

}
