package com.etherbet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Created by KAI on 7/25/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeTeam {
    private int id;
    private String name;
}
