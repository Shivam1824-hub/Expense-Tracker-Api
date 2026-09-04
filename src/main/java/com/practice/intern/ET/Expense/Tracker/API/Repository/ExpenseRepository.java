package com.practice.intern.ET.Expense.Tracker.API.Repository;

import com.practice.intern.ET.Expense.Tracker.API.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

}
