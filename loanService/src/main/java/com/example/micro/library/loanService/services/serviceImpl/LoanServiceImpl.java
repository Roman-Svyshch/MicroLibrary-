package com.example.micro.library.loanService.services.serviceImpl;

import com.example.micro.library.loanService.dto.LoanDto;
import com.example.micro.library.loanService.exception.LoanNotFoundException;
import com.example.micro.library.loanService.model.Loan;
import com.example.micro.library.loanService.repository.LoanRepository;
import com.example.micro.library.loanService.mapper.LoanMapper;
import com.example.micro.library.loanService.services.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public LoanDto issueLoan(LoanDto loanDto) {
        if (loanDto.getUserId() == null || loanDto.getBookId() == null || loanDto.getIssueDate() == null || loanDto.getDueDate() == null) {
            throw new IllegalArgumentException("Loan details cannot be null");
        }

        Loan loan = LoanMapper.mapToLoan(loanDto, new Loan());
        loan.setReturned(false);
        Loan savedLoan = loanRepository.save(loan);
        return LoanMapper.mapToLoanDto(savedLoan, new LoanDto());
    }


    @Override
    public LoanDto returnLoan(Long loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            loan.setReturned(true);
            Loan updatedLoan = loanRepository.save(loan);
            return LoanMapper.mapToLoanDto(updatedLoan, new LoanDto());
        } else {
            throw new LoanNotFoundException("Loan not found with id " + loanId);
        }
    }

    @Override
    public List<LoanDto> getLoansByUserId(Long userId) {
        List<Loan> loans = loanRepository.findByUserId(userId);
        if (loans.isEmpty()) {
            throw new LoanNotFoundException("No loans found for user with id " + userId);
        }
        return loans.stream()
                .map(loan -> LoanMapper.mapToLoanDto(loan, new LoanDto()))
                .toList();
    }

    @Override
    public List<LoanDto> getLoansByBookId(Long bookId) {
        List<Loan> loans = loanRepository.findByBookId(bookId);
        if (loans.isEmpty()) {
            throw new LoanNotFoundException("No loans found for book with id " + bookId);
        }
        return loans.stream()
                .map(loan -> LoanMapper.mapToLoanDto(loan, new LoanDto()))
                .toList();
    }
}
