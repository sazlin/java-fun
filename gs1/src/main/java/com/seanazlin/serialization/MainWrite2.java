package com.seanazlin.serialization;

import java.io.*;

public class MainWrite2 {
    public static void main(String [] args){
        Department department = new Department("id", "name", "location");
        File f = new File("serfolder");
        if(!f.exists()){
            f.mkdir();
        }
        System.out.println(f.getAbsolutePath());

        try (
                FileOutputStream stream = new FileOutputStream(f.getAbsolutePath() + File.separator + "file.ser");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        ){
            objectOutputStream.writeObject(department);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
