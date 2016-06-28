package io.pivotal.tg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
public class ServiceDiscovererApplication {

    private Log log = LogFactory.getLog(getClass());


    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscovererApplication.class, args);
    }


    @Autowired
    private RestTemplate rest;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/techniques-rest", method = RequestMethod.GET)
    public String techniquesRest() {
       return new CommandTechniques(rest).execute().toString();
    }
}
