package com.prashast.service2.controller;

import com.prashast.service2.model.Person;
import com.prashast.service2.service.RemoteService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("service2")
public class Service2Controller {

    @Autowired
    private RemoteService1 remoteService1;

    public RemoteService1 getRemoteService1() {
        return remoteService1;
    }

    public void setRemoteService1(RemoteService1 remoteService1) {
        this.remoteService1 = remoteService1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPersons")
    public List<Person> listPersons(){
        Person p = new Person();
        p.setAge(20);
        p.setFirstName("prashast");
        p.setLastName("saxena");
        return remoteService1.getPersons(p);
    }
}
