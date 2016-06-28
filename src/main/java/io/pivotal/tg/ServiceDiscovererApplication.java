package io.pivotal.tg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServiceDiscovererApplication {

    private Log log = LogFactory.getLog(getClass());


    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscovererApplication.class, args);
    }


    @Autowired
    private RestTemplate rest;

    @Autowired
    private DiscoveryClient discoveryClient;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/techniques-rest", method = RequestMethod.GET)
    public String techniquesRest() {
        try {
            URI uri = UriComponentsBuilder.fromUriString("https://training-grounds/techniques")
                    .build()
                    .toUri();

            Object[] forNow = rest.getForObject(uri, Object[].class);
            return mapper.writeValueAsString(forNow);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
