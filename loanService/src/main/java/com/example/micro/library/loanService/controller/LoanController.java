package com.example.micro.library.loanService.controller;

import com.example.micro.library.loanService.dto.LoanDto;
import com.example.micro.library.loanService.dto.LoansContactInfoDto;
import com.example.micro.library.loanService.services.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final ILoanService loanService;

    @Autowired
    private Environment environment;

    @Autowired
    private LoansContactInfoDto loansContactInfoDto;

    @Autowired
    public LoanController(ILoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/issue")
    public ResponseEntity<LoanDto> issueLoan(@RequestBody LoanDto loanDto) {
        LoanDto issuedLoan = loanService.issueLoan(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(issuedLoan);
    }

    @PutMapping("/return/{loanId}")
    public ResponseEntity<LoanDto> returnLoan(@PathVariable Long loanId) {
        LoanDto returnedLoan = loanService.returnLoan(loanId);
        return ResponseEntity.ok(returnedLoan);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDto>> getLoansByUserId(@PathVariable Long userId) {
        List<LoanDto> loans = loanService.getLoansByUserId(userId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanDto>> getLoansByBookId(@PathVariable Long bookId) {
        List<LoanDto> loans = loanService.getLoansByBookId(bookId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto > getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }
}
