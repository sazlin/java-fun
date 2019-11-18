package com.seanazlin.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Department implements Serializable {
    private String id;
    private String name;
    private String location;

}
