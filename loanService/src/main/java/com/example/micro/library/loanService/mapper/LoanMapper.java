package com.example.micro.library.loanService.mapper;

import com.example.micro.library.loanService.dto.LoanDto;
import com.example.micro.library.loanService.model.Loan;

public class LoanMapper {

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {
        loan.setUserId(loanDto.getUserId());
        loan.setBookId(loanDto.getBookId());
        loan.setIssueDate(loanDto.getIssueDate());
        loan.setDueDate(loanDto.getDueDate());
        loan.setReturned(loanDto.isReturned());
        return loan;
    }

    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto) {
        loanDto.setUserId(loan.getUserId());
        loanDto.setBookId(loan.getBookId());
        loanDto.setIssueDate(loan.getIssueDate());
        loanDto.setDueDate(loan.getDueDate());
        loanDto.setReturned(loan.isReturned());
        return loanDto;
    }
}
