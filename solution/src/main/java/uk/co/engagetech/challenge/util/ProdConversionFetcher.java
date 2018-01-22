package uk.co.engagetech.challenge.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.co.engagetech.challenge.exceptions.RateUnavailableException;
import uk.co.engagetech.challenge.resources.FixerResource;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Fetches rates from fixer.io and caches (write) the values for
 * 5 minutes to avoid abusing the service
 */
@Component
@Profile("prod")
public class ProdConversionFetcher implements ConversionFetcher {

    // we need to cache based on some key
    private static final String BASE_CURR_KEY = "GPB";

    private RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(ConversionFetcher.class);

    private CacheLoader<String, Float> cacheLoader;
    private LoadingCache<String, Float> rateCache;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        cacheLoader = new CacheLoader<String, Float>() {
            @Override
            public Float load(String key) throws Exception {
                return fetchEurRate();
            }
        };
        rateCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(cacheLoader);
    }

    @Override
    public Float getEurRate() {
        return rateCache.getUnchecked(BASE_CURR_KEY);
    }

    private Float fetchEurRate() {
        try {
            FixerResource resource = restTemplate.getForEntity("https://api.fixer.io/latest?base=GBP&symbols=EUR", FixerResource.class).getBody();
            Float euroRate = resource.getRates().getEur();
            log.info("Fetched GPB - EUR rate: " + euroRate);
            return euroRate;
        } catch (RestClientException e) {
            throw new RateUnavailableException(e);
        }
    }
}
