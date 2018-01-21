package uk.co.engagetech.challenge.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uk.co.engagetech.challenge.domain.Expense;
import uk.co.engagetech.challenge.util.DateUtil;
import uk.co.engagetech.challenge.util.PriceProcessor;

@Component
public class ExpenseConverter implements Converter<Expense, ExpenseResource> {

    @Autowired
    private PriceProcessor priceProcessor;

    @Override
    public ExpenseResource convert(Expense source) {
        ExpenseResource resource = new ExpenseResource();
        resource.setAmount(priceProcessor.toTwoDigitFloatString(source.getAmount()));
        resource.setVat(priceProcessor.toTwoDigitFloatString(source.getVat()));
        resource.setDate(DateUtil.toClientDate(source.getDate()));
        resource.setReason(source.getReason());

        return resource;
    }
}
