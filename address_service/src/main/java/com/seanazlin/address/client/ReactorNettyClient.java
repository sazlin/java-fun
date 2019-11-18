package com.seanazlin.address.client;

import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

public class ReactorNettyClient {
    public static void main(String[] args) {
        HttpClient client = HttpClient.create().baseUrl("http://jsonplaceholder.typicode.com");

        String responseBody = client.get()
                .uri("/todos")
                .responseSingle((resp,bodyBytes)->{
                    System.out.println(resp.status());
                    return bodyBytes.asString();
                })
                .block();
        System.out.println(responseBody);

        // As flux
        ByteBufFlux responseFlux = client.get().uri("/todos").responseContent();
        System.out.println(responseFlux.aggregate().asString().block());

    }
}
