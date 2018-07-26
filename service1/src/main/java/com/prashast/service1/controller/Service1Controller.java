package com.prashast.service1.controller;


import com.prashast.service1.model.Person;
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
}