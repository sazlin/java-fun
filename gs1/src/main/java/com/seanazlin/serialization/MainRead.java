package com.seanazlin.serialization;

import java.io.*;

public class MainRead {
    public static void main(String [] args){
        Department department = null;
        File f = new File("serfolder");
        if(!f.exists()){
            System.exit(1);
        }
        System.out.println(f.getAbsolutePath());

        FileInputStream stream = null;
        ObjectInputStream objectInputStream = null;
        try {
            stream = new FileInputStream(f.getAbsolutePath() + File.separator + "file.ser");
            objectInputStream = new ObjectInputStream(stream);
            department = (Department)objectInputStream.readObject();
            System.out.println(department);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
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
