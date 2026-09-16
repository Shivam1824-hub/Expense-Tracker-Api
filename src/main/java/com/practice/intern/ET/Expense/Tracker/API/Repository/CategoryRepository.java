package com.practice.intern.ET.Expense.Tracker.API.Repository;

import com.practice.intern.ET.Expense.Tracker.API.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
