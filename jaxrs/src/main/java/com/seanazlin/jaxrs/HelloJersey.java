package com.seanazlin.jaxrs;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

// JAX / Servlet 2.x Example
// Jersey 2.x
// TODO 1: Do a project using this example using Tomcat
@Path("/hello")
public class HelloJersey {
    @GET
    @Path("/printHello")
    public String printHello(){
        return "Hello";
    }

    @GET
    @Path("/printResponse")
    public Response printResponse(){
        return Response.status(200)
                .entity("response from Jersey")
                .build();
    }

    @GET
    @Path("/printParam/{name}/{city}")
    public Response printParam(@PathParam(value = "name") String name,
                               @PathParam(value = "city") String city){
        return Response.status(200)
                .entity("response from Jersey to " + name + " from " + city)
                .build();
    }

    @GET
    @Path("/printParamRegular/{name:[a-zA-Z]+}/{city:[a-zA-Z]+}")
    public Response printParamRegular(@PathParam(value = "name") String name,
                                      @PathParam(value = "city") String city){
        return Response.status(200)
                .entity("response from Jersey to " + name + " from " + city)
                .build();
    }

    //Ex: http://localhost:8080/jax-rs/rest/hello/printQueryParam/?name=sean&city=Boston
    @GET
    @Path("/printQueryParam")
    public Response printQueryParam(@DefaultValue("john") @QueryParam(value = "name") String name,
                                    @DefaultValue("Boston") @QueryParam(value = "city") String city){
        return Response.status(200)
                .entity("response from Jersey to " + name + " from " + city)
                .build();
    }

    @GET
    @Path("/printQueryParamList")
    public Response printQueryParamList(@QueryParam(value = "names") List<String> names,
                                        @QueryParam(value = "city") String city){
        return Response.status(200)
                .entity("response from Jersey to " + names + " from " + city)
                .build();  // ex. response from Jersey to [sean,john,sally] from seattle
    }

    // for arbitrary query params
    @GET
    @Path("/printContext")
    public Response printContext(@Context UriInfo info){
        ;
        return Response.status(200)
                .entity(info.getQueryParameters())
                .build();  // ex. response from Jersey to [sean,john,sally] from seattle
    }
}
