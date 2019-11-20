package com.seanazlin.addressv3.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seanazlin.addressv3.models.Address;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {
    static private List<Address> addresses = new ArrayList<>();
    static {
        addresses.addAll(Arrays.asList(
                new Address("1", "John Doe", "123 Ez street", "apt 2", "Beverly", "MA", "USA", "01915"),
                new Address("2", "Jane Doe", "567 Ez street", "apt 1", "Beverly", "MA", "USA", "01915"),
                new Address("3", "Bill Smith", "76 Hard Way", "", "Salem", "MA", "USA", "01919")
        ));
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/sample")
    public String getSampleAddress(){
        try {
            return objectMapper.writeValueAsString(addresses.get(0)); // manual use of Jackson for serialization to json
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    @GET
    @Path("/")
    public List<Address> getAddressList(){
        return addresses;
    }

    @GET
    @Path("/{id}")
    public Address getAddressById(@PathParam("id") String id){
        Address add = null;
        for(Address address : addresses){
            if (address.getId().equals(id)){
                add = address;
                break;
            }
        }
        return add;
    }

    @POST
    @Path("/")
    public Address addAddress(Address newAddress){
        addresses.add(newAddress);
        return newAddress;
    }

}
