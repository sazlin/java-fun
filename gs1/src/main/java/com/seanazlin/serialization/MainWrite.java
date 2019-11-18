package com.seanazlin.serialization;

import java.io.*;

public class MainWrite {
    public static void main(String [] args){
        Department department = new Department("id", "name", "location");
        File f = new File("serfolder");
        if(!f.exists()){
            f.mkdir();
        }
        System.out.println(f.getAbsolutePath());

        FileOutputStream stream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            stream = new FileOutputStream(f.getAbsolutePath() + File.separator + "file.ser");
            objectOutputStream = new ObjectOutputStream(stream);
            objectOutputStream.writeObject(department);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
