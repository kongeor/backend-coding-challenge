package uk.co.engagetech.challenge.expenses;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uk.co.engagetech.challenge.domain.Expense;

@Repository
public interface ExpensesRepository extends PagingAndSortingRepository<Expense, Integer> {
}
