package uk.co.engagetech.challenge.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class PriceProcessor {

    private static final double UK_VAT = 0.2f;

    private ConversionFetcher conversionFetcher;

    @Autowired
    public void setConversionFetcher(ConversionFetcher conversionFetcher) {
        this.conversionFetcher = conversionFetcher;
    }

    /**
     * Parser the following formats:
     * - "10" -> 1000
     * - "10.10" -> 10.10
     * - "10.10 EUR" -> TODO
     * @param value
     * @return the amount in cents using a BigInteger
     */
    public BigInteger fuzzyConvert(String value) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO exceptional cases
    public long calcVat(long gross) {
        double exVat = gross / (1f + UK_VAT);
        return Math.round(gross - exVat);
    }

    public long calcNet(long gross) {
        return gross - calcVat(gross);
    }

}
