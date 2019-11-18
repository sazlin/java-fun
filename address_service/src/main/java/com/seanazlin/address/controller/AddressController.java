package com.seanazlin.address.controller;

import com.seanazlin.address.model.Address;
import com.seanazlin.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping(path="/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping(path="/address")
    public List<Address> getAddressList(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addressService.getAddresses();
    }

    @GetMapping(path="/address/{id}")
    public Address getAddressById(@PathVariable("id") String id){
        Address add = null;
        for(Address address : addressService.getAddresses()){
            if (address.getId().equals(id)){
                add = address;
                break;
            }
        }
        return add;
    }

    @PostMapping(path="/address")
    public Address getAddress(@RequestBody Address newAddress){
        return addressService.addAddress(newAddress);
    }
}
