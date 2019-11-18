package com.seanazlin.address.client;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

// TODO 1: Do Rest client w/ Dummy json service (JSON Placeholder)
public class RestClient {
    public static void main(String[] args) {
////        // Old way to make a spring client request
//        AsyncRestTemplate template = new AsyncRestTemplate();
//        ListenableFuture<ResponseEntity<String>> future = template.getForEntity("http://localhost:8081/address/address/", String.class);
//        try {
//            System.out.println(future.get().getBody());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        // New Way ("Reactive")
        // Can use this approach to do multiple reqs on parallel
        // can chain onStatus
        WebClient client = WebClient.create();
        WebClient.ResponseSpec responseSpec = client.method(HttpMethod.GET).uri("http://localhost:8081/address/address/").retrieve();
        String responseBody = responseSpec
                .onStatus(status -> status == HttpStatus.INTERNAL_SERVER_ERROR, clientResponse -> {
            // Handle this particular error status code
            return Mono.error(Exception::new);
        }).bodyToMono(String.class)
                .blockOptional(Duration.of(1, ChronoUnit.SECONDS))
                .get();
    }
}