package uk.co.engagetech.challenge.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FixerRatesResource {

    @JsonProperty("GBP")
    private Float gbp;

    public Float getGbp() {
        return gbp;
    }

    public void setGbp(Float gbp) {
        this.gbp = gbp;
    }
}
