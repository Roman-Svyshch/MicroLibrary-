package com.example.micro.library.loanService.services;

import com.example.micro.library.loanService.dto.LoanDto;
import java.util.List;

public interface ILoanService {
    LoanDto issueLoan(LoanDto loanDto);
    LoanDto returnLoan(Long loanId);
    List<LoanDto> getLoansByUserId(Long userId);
    List<LoanDto> getLoansByBookId(Long bookId);
}
