package uk.co.engagetech.challenge.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FixerRatesResource {

    @JsonProperty("EUR")
    private Float eur;

    public Float getEur() {
        return eur;
    }

    public void setEur(Float eur) {
        this.eur = eur;
    }
}
