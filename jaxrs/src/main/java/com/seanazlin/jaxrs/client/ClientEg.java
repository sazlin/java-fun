package com.seanazlin.jaxrs.client;


import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientEg {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Basic JAX-RS client - Common
        String exampleGETUrl = "http://localhost:8080/jax-rs/rest/example/printResponse";
        String examplePOSTUrl = "http://localhost:8080/jax-rs/rest/example/formParam";

        Future<Response> respFuture = null;
        Client client = null;
        try {
            client = JerseyClientBuilder.createClient();

            // GET
            Invocation.Builder requestBuilder = client.target(exampleGETUrl).request();
            respFuture = requestBuilder.buildGet().submit();
            System.out.println(respFuture.get().readEntity(String.class));

            // POST
            Invocation.Builder requestBuilder2 = client.target(examplePOSTUrl).request();
            Form form = new Form();
            form.param("param1", "john").param("param2", "doe");
            respFuture = requestBuilder2.buildPost(Entity.form(form)).submit();
            System.out.println(respFuture.get().readEntity(String.class));

        } finally {
            client.close();
        }
    }
}
