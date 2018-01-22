package uk.co.engagetech.challenge.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestConversionFetcher implements ConversionFetcher {

    @Override
    public Float getEurRate() {
        return 1.2f;
    }
}
