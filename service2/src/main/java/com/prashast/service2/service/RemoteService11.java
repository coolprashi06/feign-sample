package com.prashast.service2.service;

import com.prashast.service2.model.Address;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

public interface RemoteService11 {

    @RequestLine(value = "POST /createAddress", decodeSlash = true)
    Address getAddress(@RequestBody Address address);
}
