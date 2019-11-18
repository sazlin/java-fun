package com.seanazlin.textUtils;

import com.google.common.collect.Streams;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.ls.LSOutput;

import javax.swing.plaf.IconUIResource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

// TODO: 1 - Use multithreading to see if this will improve performance
// use mix of FileUtils and StringUtils
public class TextUtilMulti {
    public static String doWork(String s){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        return s;
    }

    public static void main(String[] args){
        final String LOCATION = "/Users/seanazlin/IdeaProjects/gs1/serfolder/text.txt";
        ExecutorService exec = Executors.newFixedThreadPool(100);
        String randomText = "I like to run fast";
        long start = System.currentTimeMillis();
        try {
            String content = FileUtils.readFileToString(new File(LOCATION), "UTF-8");

            long mark1 = System.currentTimeMillis();
            String[] words = StringUtils.split(content, ' ');

            long mark2 = System.currentTimeMillis();
            Arrays.stream(words).parallel().forEach(TextUtilMulti::doWork);

            long mark3 = System.currentTimeMillis();
            for(String word : words){
                doWork(word);
            }

            long mark4 = System.currentTimeMillis();
            for(String word : words){
                exec.submit(()-> doWork(word));
            }
            exec.shutdown();
            exec.awaitTermination(20, TimeUnit.SECONDS);

            long mark5 = System.currentTimeMillis();

            System.out.println("file read: " + (mark1 - start));
            System.out.println("split: " + (mark2 - mark1));
            System.out.println("parallel: " + (mark3 - mark2));
            System.out.println("for loop: " + (mark4 - mark3));
            System.out.println("fixedExecPool: " + (mark5 - mark4));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
