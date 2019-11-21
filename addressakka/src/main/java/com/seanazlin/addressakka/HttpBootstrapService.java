package com.seanazlin.addressakka;

import akka.actor.ActorSystem;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.settings.ServerSettings;
import java.util.Optional;
import java.util.concurrent.*;

// Example 2: service example using experimental Akka Http Bootstrap from here: https://doc.akka.io/docs/akka-http/current/routing-dsl/HttpApp.html
class HttpBootstrapService extends HttpApp {

    @Override
    protected Route routes() {
        return path("hello", () ->
                get(() ->
                        complete("<h1>Say hello to akka-http</h1>")
                )
        );
    }

    private void cleanUpResources() {
        System.out.println("Cleaning up!");
    }

    @Override
    protected void postServerShutdown(Optional<Throwable> failure, ActorSystem system) {
        cleanUpResources();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Starting the server
        ActorSystem system = ActorSystem.apply("myActorSystem");
        new HttpBootstrapService().startServer("localhost", 8080, ServerSettings.create(system), system);
    }
}
