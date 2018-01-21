package uk.co.engagetech.challenge.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;

@Component
public class PriceProcessor {

    private static final double UK_VAT = 0.2f;

    private ConversionFetcher conversionFetcher;

    @Autowired
    public void setConversionFetcher(ConversionFetcher conversionFetcher) {
        this.conversionFetcher = conversionFetcher;
    }

    /**
     * Parses the following formats:
     * - "10" -> 1000
     * - "10.10" -> 10.10
     * - "10.10 EUR" -> TODO
     * @param value
     * @return the amount in cents using a BigInteger
     */
    public long fuzzyConvert(String value) {
        if (!StringUtils.isEmpty(value)) {
            value = StringUtils.trimWhitespace(value);
            String[] tokens = value.split(" ");
            if (tokens.length == 1) { // no currency
                return getPrice(value);
            } else if (tokens.length == 2 && tokens[1].equalsIgnoreCase("eur")) {
                return Math.round(getPrice(tokens[0]) / conversionFetcher.getEurRate());
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    private long getPrice(String value) {
        String[] tokens = value.split("\\.");
        try {
            if (tokens.length == 0) {
                return Long.parseLong(value) * 100;
            } else if (tokens.length == 1) {
                return Long.parseLong(tokens[0]) * 100;
            } else if (tokens.length == 2) {
                return Long.parseLong(tokens[0]) * 100
                        + Long.parseLong(tokens[1]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    // TODO exceptional cases
    public long calcVat(long gross) {
        double exVat = gross / (1f + UK_VAT);
        return Math.round(gross - exVat);
    }

    public long calcNet(long gross) {
        return gross - calcVat(gross);
    }

    public String toTwoDigitFloatString(Long cents) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        if (cents == null) {
            return null;
        } else {
            return df.format(cents / 100f);
        }
    }

}
