package com.etherbet.ipfs.service;

import com.etherbet.ipfs.IpfsManagerFactory;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by KAI on 7/24/18.
 */
@Service
public class IpfsServiceImpl implements IpfsService<MerkleNode> {

    @Autowired
    public IpfsManagerFactory ipfsManagerFactory;

    @Value("${ipfs.multiadress}")
    public String multiAddress;

    @Override
    public MerkleNode addBytes(String path, byte[] data) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(path, data);
       return ipfsManagerFactory.getIpfsInstance(multiAddress).add(file).get(0);
    }

    @Override
    public byte[] getBytes(String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        return ipfsManagerFactory.getIpfsInstance(multiAddress).cat(filePointer);
    }

    @Override
    public MerkleNode addFile(String path) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(path));
        return ipfsManagerFactory.getIpfsInstance(multiAddress).add(file).get(0);
    }

    @Override
    public void publish(String hash) throws IOException {
        Multihash multihash = Multihash.fromBase58(hash);
        ipfsManagerFactory.getIpfsInstance(multiAddress).name.publish(multihash);
    }
}
