package com.seanazlin.addressakka;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import com.seanazlin.addressakka.models.Address;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static akka.http.javadsl.server.PathMatchers.longSegment;

// Example 1: Simple Akka HTTP-based service, mostly from here: https://doc.akka.io/docs/akka-http/current/introduction.html
public class HttpServerMinimalExampleTest extends AllDirectives {
    static private List<Address> addresses = new ArrayList<>();
    static {
        addresses.addAll(Arrays.asList(
                new Address("1", "John Doe", "123 Ez street", "apt 2", "Beverly", "MA", "USA", "01915"),
                new Address("2", "Jane Doe", "567 Ez street", "apt 1", "Beverly", "MA", "USA", "01915"),
                new Address("3", "Bill Smith", "76 Hard Way", "", "Salem", "MA", "USA", "01919")
        ));
    }

    public static void main(String[] args) throws Exception {
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        //In order to access all directives we need an instance where the routes are define.
        HttpServerMinimalExampleTest app = new HttpServerMinimalExampleTest();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read(); // let it run until user presses return

        binding
                .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
                .thenAccept(unbound -> system.terminate()); // and shutdown when done
    }

    private Route createRoute() {
        final Random rnd = new Random();
        Source<Integer, NotUsed> numbers = Source.fromIterator(() -> Stream.generate(rnd::nextInt).iterator());
        return concat(
                    get(() ->
                            pathPrefix("address", () ->
                                    path(longSegment(), (Long id) -> {
                                        final CompletionStage<Optional<Address>> futureMaybeAddress = fetchAddress(String.valueOf(id));
                                        return onSuccess(futureMaybeAddress, maybeAddress ->
                                                maybeAddress.map(address -> completeOK(address, Jackson.marshaller()))
                                                            .orElseGet(() -> complete(StatusCodes.NOT_FOUND, "Not Found"))
                                        );
                                    }))),
                    path("random", () ->
                            get(() ->
                                    complete(HttpEntities.create(ContentTypes.TEXT_PLAIN_UTF8,
                                            // transform each number to a chunk of bytes
                                            numbers.map(x -> ByteString.fromString(x + "\n"))))))
        );

    }

    // (fake) async database query api
    private CompletionStage<Optional<Address>> fetchAddress(String addressId) {
        return CompletableFuture.completedFuture(Optional.of(addresses.get(0)));
    }


}
