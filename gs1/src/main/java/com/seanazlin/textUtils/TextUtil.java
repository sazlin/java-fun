package com.seanazlin.textUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

// TODO: 1 - Use multithreading to see if this will improve performance
// use mix of FileUtils and StringUtils
// TODO: 2 - write program to convert https://jsonplaceholder.typicode.com/todos to csv file
// use multithreading w/ blocking queue
// consider converting downloaded json to a map, use objectmapper, and then use threads to convert to pojo
// use jackson or json libs
public class TextUtil {
    public static void main(String[] args){
        final String LOCATION = "/Users/seanazlin/IdeaProjects/gs1/serfolder/text.txt";
        String randomText = "I like to run fast";
        try {
            String content = FileUtils.readFileToString(new File(LOCATION), "UTF-8");
            String[] words = StringUtils.split(content, ' ');
            for(String word : words){
                System.out.println(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
