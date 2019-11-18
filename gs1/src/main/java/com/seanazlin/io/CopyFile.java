package com.seanazlin.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args){
        String fromWhereToCopy = args[0];
        String toCopyLocation = args[1];
        try {
            FileUtils.copyFile(new File(fromWhereToCopy), new File(toCopyLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
