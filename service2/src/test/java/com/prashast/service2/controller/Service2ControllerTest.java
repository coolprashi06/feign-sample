package com.prashast.service2.controller;

import com.prashast.service2.model.Person;
import com.prashast.service2.service.RemoteService1;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class Service2ControllerTest {

    private MockMvc mockMvc;

    @Test
    public void testMethod() throws Exception{
        String json = "[\n" +
                "    {\n" +
                "        \"firstName\": \"prashast\",\n" +
                "        \"lastName\": \"saxena\",\n" +
                "        \"age\": 20\n" +
                "    }\n" +
                "]";

        Person p = new Person();
        p.setAge(20);
        p.setFirstName("prashast");
        p.setLastName("saxena");

        List<Person> personList = new ArrayList<>();
        personList.add(p);

        RemoteService1 service1 = Mockito.mock(RemoteService1.class);
        //Mockito.when(service1.getPersons(Mockito.any(Person.class))).thenReturn(personList);

        Service2Controller controller = new Service2Controller();
        controller.setRemoteService1(service1);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/service2/getPersons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));

    }
}
