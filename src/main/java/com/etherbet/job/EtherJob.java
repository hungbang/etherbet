package com.etherbet.job;

import com.etherbet.dto.Fixture;
import com.etherbet.dto.Match;
import com.etherbet.ipfs.service.IpfsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.math.IntMath;
import com.sun.org.apache.bcel.internal.generic.LOOKUPSWITCH;
import io.ipfs.api.MerkleNode;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@Component
public class EtherJob {
    public static final Logger LOGGER = LoggerFactory.getLogger(EtherJob.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<Integer, String> process(IpfsService ipfsService, Fixture fixture) throws IOException {
        var count = fixture.getMatches().size();
        var pageNumber = IntMath.divide(count, 10, RoundingMode.CEILING);
        Map<Integer, String> indexHash = new HashMap<>();
        LOGGER.info("EtherJob is running, matches size is {} and pageNumber {} ", count, pageNumber);
        List<List<Match>> subMatches = Lists.partition(fixture.getMatches(), pageNumber);
        final int[] index = {0};
        subMatches.stream().forEach(matches -> {
            try {
                var blockData = objectMapper.writeValueAsString(matches);
                //Push data to IPFS and return hash number
                MerkleNode merkleNode = (MerkleNode) ipfsService.addBytes(Fixture.FIXTURE_PATH, blockData.getBytes());
                // put hash result and page number to map
                indexHash.put(index[0], merkleNode.hash.toBase58());
                // increase index number
                index[0]++;
            } catch (JsonProcessingException e) {
                LOGGER.error("Error when parse array Object to string json.", e);
            } catch (IOException e) {
                LOGGER.error("Error when push data to IPFS.", e);
            }
        });
        return indexHash;
    }

}
