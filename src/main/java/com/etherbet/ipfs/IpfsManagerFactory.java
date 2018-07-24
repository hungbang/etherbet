package com.etherbet.ipfs;

import com.etherbet.core.ApplicationContextHolder;
import io.ipfs.api.IPFS;
import org.springframework.core.env.Environment;

/**
 * Created by KAI on 7/24/18.
 */

public class IpfsManagerFactory {

    public String multiaddress;

    private static IpfsManagerFactory ipfsManagerFactory = new IpfsManagerFactory();
    private IPFS ipfs;

    private IpfsManagerFactory() {
        Environment environment = (Environment) ApplicationContextHolder.getApplicationContext().getBean("environment");
        this.multiaddress = environment.getProperty("ipfs.multiadress");
    }

    public static IpfsManagerFactory getInstance() {
        return ipfsManagerFactory;
    }

    public IPFS createIpfs() {
        if (ipfs == null) {
            return new IPFS(multiaddress);
        }
        return ipfs;
    }
}
