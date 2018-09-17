package com.prashast.service1.model;

import com.prashast.service1.constraint.ValidateText;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {

    @ValidateText("addr1:") private String line1;
    private String line2;
    private String city;
    private String state;
    private Integer zip;
    private String country;
    @Valid private List<Phone> phones;
}
