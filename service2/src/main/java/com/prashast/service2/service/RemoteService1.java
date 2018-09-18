package com.prashast.service2.service;

import com.prashast.service2.model.Address;
import com.prashast.service2.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(url = "http://localhost:8484/service1", name = "remoteService1", decode404 = true)
public interface RemoteService1 {

    @RequestMapping(method = RequestMethod.POST, path = "/postResource", consumes = "application/json")
    public ResponseEntity<List<Person>> getPersons(Person person);

    @RequestMapping(method = RequestMethod.POST, path = "/createAddress", consumes = "application/json")
    public Address getAddress(Address address);
}
