package com.seanazlin.jaxrs;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartMediaTypes;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// Tomcat / Jersey 2.x / Servlet 2.x Example
@Path("/example")
public class ExampleResource {
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

        return Response.status(200)
                .entity(info.getQueryParameters())
                .build();  // ex. response from Jersey to [sean,john,sally] from seattle
    }


    // EX: http://localhost:8080/addressv2/address/matrixParam;param1=john;param2=doe
    @GET
    @Path("/matrixParam")
    public Response matrixParam(@MatrixParam("param1") String param1,
                                @MatrixParam("param2") String param2){

        return Response.status(200)
                .entity(param1 + " | " + param2)
                .build();
    }

    @POST
    @Path("/formParam")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response formParam(@FormParam("param1") String param1,
                              @FormParam("param2") String param2){

        return Response.status(200)
                .entity(param1 + " | " + param2)
                .build();
    }

    @GET
    @Path("/headerParam")
    public Response headerParam(@HeaderParam("user-agent") String userAgent){
        return Response.status(200)
                .entity(userAgent)
                .build();
    }

    @GET
    @Path("/printHeaders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response printHeaders(@Context HttpHeaders headers){
        return Response.status(200)
                .entity(headers.getRequestHeaders())
                .build();
    }

    @GET
    @Path("/textFileDownload")
    @Produces(MediaType.TEXT_PLAIN)
    public Response textFileDownload(){
        Response resp = Response.status(200)
                .entity(new File("/Users/seanazlin/IdeaProjects/jaxrs/someFile.txt"))
                .header("Content-Disposition", "attachment;filename=\"someFile.txt\"")
                .build();
        return resp;
    }


    // TODO 1: Make it possible to download PDF and Excel spreadsheets
    @GET
    @Path("/imageFileDownload")
    @Produces("image/png")
    public Response imageFileDownload(){
        Response resp = Response.status(200)
                .entity(new File("/Users/seanazlin/IdeaProjects/jaxrs/somePNG.png"))
                .header("Content-Disposition", "attachment;filename=\"aPng.png\"")
                .build();
        return resp;
    }

    @POST
    @Path("/imageFileUpload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response imageFileUpload(@FormDataParam("file") InputStream uploadedFile,
                                    @FormDataParam("file") FormDataContentDisposition contentDisposition) {
        String fileName = "temp_" + contentDisposition.getFileName() + Math.random();
        String filePath = "/Users/seanazlin/IdeaProjects/jaxrs/" + fileName;
        try {
            FileUtils.copyInputStreamToFile(uploadedFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response resp = Response.status(200).entity(fileName).build();
        return resp;
    }
}
