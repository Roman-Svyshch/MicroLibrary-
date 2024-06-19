package com.example.micro.library.loanService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

    private Long  userId;
    private Long bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private boolean returned;

}
