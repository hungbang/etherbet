package com.etherbet.ipfs;

import io.ipfs.api.IPFS;
import org.springframework.stereotype.Component;

/**
 * Created by KAI on 7/24/18.
 */
@Component
public class IpfsManagerFactory {
    public IPFS getIpfsInstance(String multiaddress){
        return IpfsCreator.getIpfs(multiaddress);
    }
}
