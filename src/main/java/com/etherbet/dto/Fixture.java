package com.etherbet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Created by KAI on 7/25/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Fixture {
    @JsonProperty("competition")
    private Competition competition;
    @JsonProperty("matches")
    private List<Match> matches;
}
