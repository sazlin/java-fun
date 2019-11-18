package com.seanazlin.address.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class JsonPlaceholderClient {
    public static void main(String[] args) {
        WebClient client = WebClient.create("http://jsonplaceholder.typicode.com");
        Mono<String> responseMono = client.method(HttpMethod.GET)
                .uri("/todos")
                .retrieve()
                .bodyToMono(String.class);
        String responseBody = responseMono.block();
        System.out.println(responseBody);
    }
}
