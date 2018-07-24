package com.etherbet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"area"}, ignoreUnknown = true)
public class Competition {
    private String id;
    private String name;
}
