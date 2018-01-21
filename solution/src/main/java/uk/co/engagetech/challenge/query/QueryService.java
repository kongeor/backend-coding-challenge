package uk.co.engagetech.challenge.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.engagetech.challenge.util.PriceProcessor;

@Service
public class QueryService {

    @Autowired
    private PriceProcessor priceProcessor;

    public VatResource fetchVatFromAmount(String amountStr) {
        long amount = priceProcessor.fuzzyParse(amountStr);
        long vat = priceProcessor.calcVat(amount);
        String formattedVat = priceProcessor.toTwoDigitFloatString(vat);
        VatResource resource = new VatResource();
        resource.setVat(formattedVat);
        return resource;

    }
}
