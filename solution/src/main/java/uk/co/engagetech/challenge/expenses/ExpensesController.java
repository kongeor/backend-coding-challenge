package uk.co.engagetech.challenge.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService service;

    @PostMapping
    public ResponseEntity<ExpenseResource> createExpense(@Valid @RequestBody ExpenseResource expense) {
        return ResponseEntity.ok(service.createExpense(expense));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResource>> fetchExpenses() {
        return ResponseEntity.ok(service.fetchAll());
    }

}
