package com.prashast.service1.controller;


import com.prashast.service1.constraint.ValidateText;
import com.prashast.service1.model.Address;
import com.prashast.service1.model.Person;
import com.prashast.service1.service.ServiceHelper;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/service1")
public class Service1Controller {

    @Autowired
    private ServiceHelper serviceHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/getResource")
    public String getResource(){
        return "hello! this is service!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/postResource")
    public List<Person> postResource(@RequestBody @Valid Person person){
        System.out.println("person received" + person.toString());
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        return personList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRemoteObjects")
    public void retrieveObjectsAsync()throws Exception{
        serviceHelper.retrieveObjectsOtherWay();
    }

    /*
    below 2 resources make use lombok to generate boilerplate code of getter/setter/toString()/equals() and hashcode()
     */

    @RequestMapping(method = RequestMethod.GET, value = "/getCreatedAddress")
    public Address getCreatedAddress(){
        Address address = new Address();
        address.setLine1("345 park ave");
        address.setLine2("Almaden Blvd");
        address.setCity("San Jose");
        address.setState("California");
        address.setCountry("USA");
        address.setZip(95136);
        System.out.println(address.toString());
        return address;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createAddress")
    public Address createAddress(@RequestBody @Valid Address address){
        System.out.println(address.toString());
        System.out.println(address.getLine1());
        return address;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validateText/{someid}")
    public String validateText(@PathVariable("someid") @ValidateText("valid_") String someId){
        System.out.println("value of some id is "+ someId);
        return someId;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validateText1/{someid}")
    public ResponseEntity<String> validateText1(@PathVariable("someid") String someId){
        System.out.println("value of some id is "+ someId);
        if(!isValid(someId)){
            System.out.println(" and is invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(someId, HttpStatus.OK);
    }

    private boolean isValid(String someid) {
        boolean valid = false;

        try {
            ESAPI.validator().getValidInput("SomeId", someid, "SafeString", 30, false, true);
            valid = true;
        } catch (IntrusionException | ValidationException e) {
            System.out.println(e.getMessage());
        }

        return valid;
    }


}
