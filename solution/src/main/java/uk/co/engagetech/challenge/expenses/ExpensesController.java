package uk.co.engagetech.challenge.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.engagetech.challenge.domain.Expense;

@RestController
@RequestMapping("/app/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesRepository repository;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        System.out.println(expense);
        // TODO
        return ResponseEntity.ok(repository.save(expense));
    }

    @GetMapping
    public ResponseEntity<Iterable<Expense>> fetchExpenses() {
        // TODO;
        return ResponseEntity.ok(repository.findAll());
    }


}
