package com.prashast.service1.controller;

import com.prashast.service1.model.Address;
import com.prashast.service1.model.Person;
import com.prashast.service1.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
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
        serviceHelper.retrieveObjects();
    }


    /**
     * this will take address object from request body and return the same object.
     * testing lombok properties inside the method
     * @param address
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createAddress")
    public Address validateAndCreateAddress(@RequestBody @Valid Address address){
        System.out.println("address received : "+address.toString());
        return address;
    }

}
