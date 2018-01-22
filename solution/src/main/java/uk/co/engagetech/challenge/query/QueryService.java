package uk.co.engagetech.challenge.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.engagetech.challenge.util.PriceProcessor;

@Service
public class QueryService {

    @Autowired
    private PriceProcessor priceProcessor;

    public VatResource fetchVatFromAmount(String amountStr) {
        VatResource resource = new VatResource();
        resource.setVat(getFormattedVat(amountStr));
        return resource;
    }

    private String getFormattedVat(String amountStr) {
        try {
            long amount = priceProcessor.fuzzyParse(amountStr);
            long vat = priceProcessor.calcVat(amount);
            return priceProcessor.toTwoDigitFloatString(vat);
        } catch (IllegalArgumentException e) {
            return "N/A";
        }
    }
}
