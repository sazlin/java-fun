package com.seanazlin.addressv2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String id;
    private String name;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
