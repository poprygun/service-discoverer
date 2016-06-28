package io.pivotal.tg;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class CommandTechniques extends HystrixCommand {
    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();


    protected CommandTechniques(RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("Dojo"));
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {

        URI uri = UriComponentsBuilder.fromUriString("https://training-grounds/techniques")
                .build()
                .toUri();

        Object[] forNow = restTemplate.getForObject(uri, Object[].class);
        return mapper.writeValueAsString(forNow);
    }

    @Override
    protected String getFallback() {
            return "[{\"name\":\"irimi nage\"},{\"name\":\"shiho nage\"}]";
    }
}
