package com.seanazlin.textUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.google.common.collect.Streams;
import lombok.Synchronized;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

// TODO: 2 - write program to convert https://jsonplaceholder.typicode.com/todos to csv file
// use multithreading w/ blocking queue
// consider converting downloaded json to a map, use objectmapper, and then use threads to convert to pojo
// use jackson or json libs
public class JSONToCSVMultiBQ {
    private static ObjectMapper mapper = new ObjectMapper();
    private static ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(20);
    private static ExecutorService exec = Executors.newFixedThreadPool(8);
    private static Set<Book> books = ConcurrentHashMap.newKeySet(200);
    private static boolean running = true;


    public static void convertJSONObjectToBook(Object object) {
        try {
            JSONObject bookJSON = (JSONObject) object;
            Book book = mapper.readValue(bookJSON.toString(), Book.class);
            books.add(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void bookPipelineWorker(){
        while(running) {
            Object object = queue.poll();
            if(object != null) {
                convertJSONObjectToBook(object);
            }
            else{
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        // Start workers to watch the queue
        IntStream.range(0, 8).forEach(x -> exec.submit(JSONToCSVMultiBQ::bookPipelineWorker));

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/todos");

        try (CloseableHttpResponse response = httpclient.execute(httpGet);
             BufferedWriter writer = new BufferedWriter(new FileWriter("books.csv")))
        {
            long mark1 = System.currentTimeMillis();
            String body = EntityUtils.toString(response.getEntity());

            long mark2 = System.currentTimeMillis();
            JSONArray jsonArray = new JSONArray(body);

            // Push work to the queue
            // Streams.stream(jsonArray).forEach(x->queue.put(x));
            for(Object object : jsonArray) {
                queue.put(object);
            }

            // Wait for queue to empty
            while(!queue.isEmpty()){
                Thread.yield();
            }
            // signal to threads to shutdown
            running = false;
            exec.shutdown();
            exec.awaitTermination(5, TimeUnit.SECONDS);
            long mark3 = System.currentTimeMillis();
            for(Book book : books){
                writer.write(book.toCSVString());
                writer.newLine();
            }
            writer.flush();
            writer.close();

            long mark4 = System.currentTimeMillis();
            System.out.println("download: " + (mark2 - mark1));
            System.out.println("convert to set of Books: " + (mark3 - mark2));
            System.out.println("write Books to csv: " + (mark4 - mark3));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
