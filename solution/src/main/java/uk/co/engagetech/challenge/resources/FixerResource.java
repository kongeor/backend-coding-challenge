package uk.co.engagetech.challenge.resources;

public class FixerResource {

    private String base;
    private String date;
    private FixerRatesResource rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FixerRatesResource getRates() {
        return rates;
    }

    public void setRates(FixerRatesResource rates) {
        this.rates = rates;
    }
}
