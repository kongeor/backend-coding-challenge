package uk.co.engagetech.challenge.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.engagetech.challenge.domain.Expense;
import uk.co.engagetech.challenge.util.DateUtil;
import uk.co.engagetech.challenge.util.PriceProcessor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExpensesService {

    @Autowired
    private PriceProcessor priceProcessor;

    @Autowired
    private ExpensesRepository repository;

    @Autowired
    private ExpenseConverter converter;

    public ExpenseResource createExpense(ExpenseResource resource) {
        Expense exp = new Expense();
        exp.setAmount(priceProcessor.fuzzyParse(resource.getAmount()));
        exp.setDate(DateUtil.parseClientDate(resource.getDate()));
        exp.setReason(resource.getReason());

        Expense saved = repository.save(exp);
        return converter.convert(saved);
    }

    public List<ExpenseResource> fetchAll() {
        Iterable<Expense> all = repository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
