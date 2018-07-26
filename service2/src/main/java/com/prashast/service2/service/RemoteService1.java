package com.prashast.service2.service;

import com.prashast.service2.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "http://localhost:8181/service1", name = "remoteService1")
public interface RemoteService1 {

    @RequestMapping(method = RequestMethod.POST, path = "/postResource", consumes = "application/json")
    public List<Person> getPersons(Person person);
}
