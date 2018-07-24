package com.etherbet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
    private Integer id;
    private String homeTeamName;
    private Integer homeId;
    private Integer awayId;
    private String awayTeamName;
    private String utcDate;
    private String status;
    private HomeTeam homeTeam;
    private AwayTeam awayTeam;
    private Score score;
    private List<Betting> bettings;

}
