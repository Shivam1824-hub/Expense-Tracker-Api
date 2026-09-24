package com.practice.intern.ET.Expense.Tracker.API.Repository;

import com.practice.intern.ET.Expense.Tracker.API.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long>, JpaSpecificationExecutor<Expense> {
    List<Expense> findByCategory_Id(Long categoryId);
}
