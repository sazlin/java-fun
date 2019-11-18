package com.seanazlin.textUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

// TODO: 2 - write program to convert https://jsonplaceholder.typicode.com/todos to csv file
// use multithreading w/ blocking queue
// consider converting downloaded json to a map, use objectmapper, and then use threads to convert to pojo
// use jackson or json libs
public class JSONToCSV {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/todos");
        try (CloseableHttpResponse response1 = httpclient.execute(httpGet);
             BufferedWriter writer = new BufferedWriter(new FileWriter("books.csv")))
        {
            long mark1 = System.currentTimeMillis();
            String body = EntityUtils.toString(response1.getEntity());

            long mark2 = System.currentTimeMillis();
            Book[] books = mapper.readValue(body, Book[].class);
            //System.out.println(Arrays.toString(books));

            long mark3 = System.currentTimeMillis();
            for (Book book : books){
                writer.write(book.toCSVString());
                writer.newLine();
            }
            writer.flush();
            writer.close();

            long mark4 = System.currentTimeMillis();
            System.out.println("download: " + (mark2 - mark1));
            System.out.println("convert to array: " + (mark3 - mark2));
            System.out.println("write to csv (for loop): " + (mark4 - mark3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
