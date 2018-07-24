package com.etherbet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

/**
 * Created by KAI on 7/25/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
    public Integer id;
    public String homeTeamName;
    public Integer homeId;
    public Integer awayId;
    public String awayTeamName;
    public String utcDate;
    public String status;
    private HomeTeam homeTeam;
    private AwayTeam awayTeam;
    public Score score;
    public List<Betting> bettings;

}
