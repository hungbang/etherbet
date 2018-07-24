package com.etherbet.ipfs.service;

import com.etherbet.ipfs.IpfsManagerFactory;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by KAI on 7/24/18.
 */
@Service
public class IpfsServiceImpl implements IpfsService<MerkleNode> {

    final IPFS ipfs = IpfsManagerFactory.getInstance().createIpfs();

    @Override
    public MerkleNode addBytes(String path, byte[] data) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(path, data);
        ipfs.add(file).get(0);
        return null;
    }

    @Override
    public byte[] getBytes(String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        return ipfs.cat(filePointer);
    }

    @Override
    public MerkleNode addFile(String path) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(path));
        return ipfs.add(file).get(0);
    }
}
