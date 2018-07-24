package com.etherbet.dto;

import lombok.*;

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
public class Betting {
    private String id;
    private String date;
    private String homeTeam;
    private String awayTeam;

}
