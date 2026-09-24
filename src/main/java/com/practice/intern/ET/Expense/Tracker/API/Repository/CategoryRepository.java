package com.practice.intern.ET.Expense.Tracker.API.Repository;

import com.practice.intern.ET.Expense.Tracker.API.Model.Category;
import com.practice.intern.ET.Expense.Tracker.API.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
