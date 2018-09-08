package com.prashast.service1.model;

import lombok.*;

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
}
