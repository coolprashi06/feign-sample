package com.prashast.service1.controller;


import com.prashast.service1.model.Address;
import com.prashast.service1.model.Person;
import com.prashast.service1.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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


}
