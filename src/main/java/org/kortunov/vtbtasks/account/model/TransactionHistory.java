package org.kortunov.vtbtasks.account.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TransactionHistory {
    private final LocalDate date;
    private final String typeOfTransaction;
    private final Account from;
    private final double amount;

}
