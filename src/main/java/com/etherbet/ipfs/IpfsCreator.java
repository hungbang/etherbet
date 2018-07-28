package com.etherbet.ipfs;

import io.ipfs.api.IPFS;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
public class IpfsCreator {

    public static CustomIPFS getIpfs(String multiAddress){
        return new CustomIPFS(multiAddress);
    }

    public static CustomIPFS getIpfs(String host, int port, String version){
        return new CustomIPFS(host, port, version , true);
    }



}
