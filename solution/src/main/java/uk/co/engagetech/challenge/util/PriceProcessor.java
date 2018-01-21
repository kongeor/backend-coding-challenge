package uk.co.engagetech.challenge.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PriceProcessor {

    private static final double UK_VAT = 0.2f;

    private static final Pattern CLIENT_AMOUNT_PATTERN =
            Pattern.compile("^(?<pounds>\\d+)(\\.(?<cents>\\d{2}))?\\s?(?<curr>[A-Z]{3})?$");

    private ConversionFetcher conversionFetcher;

    @Autowired
    public void setConversionFetcher(ConversionFetcher conversionFetcher) {
        this.conversionFetcher = conversionFetcher;
    }

    /**
     * Parses the following formats:
     * - "10" -> 1000
     * - "10.10" -> 10.10
     * - "10.10 EUR" -> will fetch the current EUR rating and do then the appropriate conversion
     * @param value the value to parse
     * @return the amount in cents using a BigInteger
     */
    public long fuzzyParse(String value) {
        if (!StringUtils.isEmpty(value)) {
            value = StringUtils.trimWhitespace(value);

            Matcher matcher = CLIENT_AMOUNT_PATTERN.matcher(value);
            if (matcher.find()) {
                String pounds = matcher.group("pounds");
                String cents = matcher.group("cents");
                if (cents == null) {
                    cents = "0";
                }
                String curr = matcher.group("curr");

                long amount = Long.parseLong(pounds) * 100 + Long.parseLong(cents);

                if (curr != null && curr.equalsIgnoreCase("EUR")) {
                    amount = Math.round(amount / conversionFetcher.getEurRate());
                }
                return amount;
            }
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
