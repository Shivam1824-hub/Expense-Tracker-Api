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
    public ResponseEntity <List<ExpenseResponseDto>> findAllExpense(){
        List<ExpenseResponseDto> ex = service.findAllExpenses();
        if(ex.isEmpty()){
            return ResponseEntity.noContent().build();
        }return ResponseEntity.ok(ex);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> findByIdExpense(@PathVariable Long id){
         ExpenseResponseDto findId = service.findByIdExpense(id);
        return ResponseEntity.ok(findId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateByIdExpense(@PathVariable Long id,@RequestBody ExpenseRequestDto updateInfo){
        ExpenseResponseDto updated = service.updateByIdExpense(id,updateInfo);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
         String message =service.deleteExpense(id);
         return ResponseEntity.ok(message);
    }

}
