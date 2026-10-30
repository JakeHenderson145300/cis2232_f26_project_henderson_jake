package ca.hccis.pocketLedger.repositories;

import ca.hccis.pocketLedger.jpa.entity.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
    // Left available for standard JPA CRUD interactions
}

