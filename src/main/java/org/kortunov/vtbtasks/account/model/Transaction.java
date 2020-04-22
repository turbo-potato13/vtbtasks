package org.kortunov.vtbtasks.account.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Builder
@RequiredArgsConstructor
public class Transaction {

    private static final AtomicLong ID_INCREMENTER = new AtomicLong();

    private final long id = ID_INCREMENTER.incrementAndGet();
    private final LocalDate date;
    private final Account fromAccount;
    private final Account toAccount;
    private final double amount;
}
