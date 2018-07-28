package com.etherbet.ipfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by KAI on 7/24/18.
 */
@Component
public class IpfsManagerFactory {

    public static final Logger LOGGER = LoggerFactory.getLogger(IpfsManagerFactory.class);
    public CustomIPFS getIpfsInstance(String multiaddress) throws MalformedURLException {
        if (multiaddress.contains("https")) {
            URL url = new URL(multiaddress);
            LOGGER.info("Initial IPFS instance with host {} and port {}", url.getHost(), url.getPort());
            return IpfsCreator.getIpfs(url.getHost(), url.getPort(), "/api/");
        }
        return IpfsCreator.getIpfs(multiaddress);
    }
}
