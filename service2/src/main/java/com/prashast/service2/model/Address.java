package com.prashast.service2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {

    private String line1;
    private String line2;
    private String city;
    private String state;
    private Integer zip;
    private String country;
    private List<Phone> phones;
}
