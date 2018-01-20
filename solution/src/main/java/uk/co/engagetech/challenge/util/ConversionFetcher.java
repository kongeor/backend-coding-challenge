package uk.co.engagetech.challenge.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.co.engagetech.challenge.exceptions.RateUnavailableException;
import uk.co.engagetech.challenge.resources.FixerResource;

import javax.annotation.PostConstruct;

@Component
public class ConversionFetcher {

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }


    public Float getEurToGpbRate() {
        try {
            FixerResource resource = restTemplate.getForEntity("https://api.fixer.io/latest", FixerResource.class).getBody();
            return resource.getRates().getGbp();
        } catch (RestClientException e) {
            throw new RateUnavailableException(e);
        }
    }
}
