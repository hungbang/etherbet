package com.etherbet.ipfs.service;

import java.io.IOException;

/**
 * Created by KAI on 7/24/18.
 */

public interface IpfsService<T> {
    /**
     * To add a byte[] use
     *
     * @param data
     * @return
     */
    T addBytes(String path, byte[] data) throws IOException;


    /**
     * To get a file use
     *
     * @param hash
     * @return
     */
    byte[] getBytes(String hash) throws IOException;

    /**
     * To add a file use (the add method returns a list of #Merklenodes, in this case there is only one element)
     *
     * @param path
     * @return
     */
    T addFile(String path) throws IOException;

}
