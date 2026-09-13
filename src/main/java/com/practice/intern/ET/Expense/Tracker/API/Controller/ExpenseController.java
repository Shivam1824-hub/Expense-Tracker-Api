package com.practice.intern.ET.Expense.Tracker.API.Controller;

import com.practice.intern.ET.Expense.Tracker.API.Model.Expense;
import com.practice.intern.ET.Expense.Tracker.API.Service.ExpenseService;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseRequestDto;
import com.practice.intern.ET.Expense.Tracker.API.dto.ExpenseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDto> addExpense(@RequestBody ExpenseRequestDto requestDto){
        ExpenseResponseDto savedExpense = service.addExpense(requestDto);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Expense> findAllExpense(){
        return service.findAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense findByIdExpense(@PathVariable Long id){
        return service.findByIdExpense(id);
    }

    @PutMapping("/{id}")
    public Expense updateByIdExpense(@PathVariable Long id,@RequestBody Expense updateInfo){
        return service.updateByIdExpense(id,updateInfo);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
         service.deleteExpense(id);
         return "data has been deleted";
    }

}
