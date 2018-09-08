package com.prashast.service1.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Getter
@Setter
@ToString
public class Phone {

    @NotNull private int countryCode;
    @NotNull private String phoneNumber;
}
