package com.seanazlin.address.service;

import com.seanazlin.address.model.Address;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AddressService {
    private List<Address> addresses = new ArrayList<>();
    {
        addresses.addAll(Arrays.asList(
                new Address("1", "John Doe", "123 Ez street", "apt 2", "Beverly", "MA", "USA", "01915"),
                new Address("2", "Jane Doe", "567 Ez street", "apt 1", "Beverly", "MA", "USA", "01915"),
                new Address("3", "Bill Smith", "76 Hard Way", "", "Salem", "MA", "USA", "01919")
        ));
    }

    public List<Address> getAddresses(){
        return addresses;
    }

    public Address addAddress(Address newAddress) {
        addresses.add(newAddress);
        return newAddress;
    }
}
