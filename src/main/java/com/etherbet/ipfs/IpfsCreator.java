package com.etherbet.ipfs;

import io.ipfs.api.IPFS;
import org.springframework.stereotype.Component;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
public class IpfsCreator {

    public static IPFS getIpfs(String multiAddress){
        return new IPFS(multiAddress);
    }

    public static IPFS getIpfs(String host, int port){
        return new IPFS(host, port);
    }



}
