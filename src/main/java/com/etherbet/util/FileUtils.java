package com.etherbet.util;

import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by KAI on 7/28/18.
 * Copyright 2018 by etherbet
 * All rights reserved.
 */
public class FileUtils {
    public static void write(String data, String path) throws IOException {
        File file = new File(path);
        if(!file.exists())
            file.createNewFile();
        try (FileOutputStream outputStream = new FileOutputStream(file.getPath())) {
            outputStream.write(data.getBytes());
        }
    }
}
