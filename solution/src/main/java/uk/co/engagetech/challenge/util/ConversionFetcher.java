package uk.co.engagetech.challenge.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.co.engagetech.challenge.exceptions.RateUnavailableException;
import uk.co.engagetech.challenge.resources.FixerResource;

import javax.annotation.PostConstruct;

@Component
public class ConversionFetcher {

    private RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(ConversionFetcher.class);

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    /**
     * Gets the EUR ratio based on GBP
     * @return the ratio
     */
    public Float getEurRate() {
        try {
            FixerResource resource = restTemplate.getForEntity("https://api.fixer.io/latest?base=GBP&symbols=EUR", FixerResource.class).getBody();
            Float euroRate = resource.getRates().getEur();
            log.info("Current GPB - EUR rate is: " + euroRate);
            return euroRate;
        } catch (RestClientException e) {
            throw new RateUnavailableException(e);
        }
    }
}
