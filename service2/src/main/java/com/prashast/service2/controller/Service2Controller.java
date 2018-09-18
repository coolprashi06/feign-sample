package com.prashast.service2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashast.service2.model.Address;
import com.prashast.service2.model.Person;
import com.prashast.service2.service.RemoteService1;
import com.prashast.service2.service.RemoteService11;
import feign.Feign;
import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
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
    public ResponseEntity<List<Person>> listPersons(){
        Person p = new Person();
        p.setAge(20);
        p.setFirstName("prashast");
        p.setLastName("saxena");
        ResponseEntity<List<Person>> responseEntity = remoteService1.getPersons(p);
        if(responseEntity.getStatusCode() != HttpStatus.OK){
            System.out.println("status code received : "+ responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return responseEntity;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAddress")
    public Address getAddress(){
        Address address = new Address();
        address.setLine1("151 S Almaden Blvd");
        address.setLine2("5th Floor");
        address.setCity("san jose");
        address.setState("CA");
        address.setCountry("USA");

        RemoteService11 remoteService11 = Feign.builder()
                .encoder(new Encoder() {
                    @Override
                    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
                        requestTemplate.header("Content-Type", "application/json");
                        requestTemplate.header("Consumes", "application/json");
                        ObjectMapper mapper = new ObjectMapper();
                        try{
                            String toSend = mapper.writer().writeValueAsString(o);
                            System.out.println("string to send: " + toSend);
                            requestTemplate.body(toSend);
                        }catch (JsonProcessingException e){
                            System.out.println("unable to convert object");
                        }
                    }
                }).errorDecoder(new ErrorDecoder() {
                    @Override
                    public Exception decode(String s, Response response) {
                        System.out.println("Status is : "+ response.status());
                        System.out.println("reason is : "+ response.reason());
                        return null;
                    }
                }).decoder(new Decoder() {
                    @Override
                    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
                        ObjectMapper mapper = new ObjectMapper();
                        return mapper.readValue(response.body().asInputStream(), Address.class);
                    }
                })
                .target(RemoteService11.class, "http://localhost:8484/service1");

        Address addressRemote = remoteService11.getAddress(address);
        if(addressRemote != null){
            System.out.println("address received : "+ addressRemote.toString());
        }
        return addressRemote;
    }
}
