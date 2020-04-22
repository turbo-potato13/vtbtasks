package org.kortunov.vtbtasks.account.model;

import lombok.*;
import org.kortunov.vtbtasks.exception.InvalidObjectException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@EqualsAndHashCode(of = {"accountNumber"})
@RequiredArgsConstructor
public class Account {
    private static final AtomicLong ID_INCREMENTER = new AtomicLong();

    @Getter
    private final long accountNumber = ID_INCREMENTER.incrementAndGet();
    @Getter
    private final Client client;
    private final Set<Transaction> transactions = new HashSet<>();

    public void addTransaction(final Transaction transaction) {
        Objects.requireNonNull(transaction);
        if (this.equals(transaction.getFromAccount()) || this.equals(transaction.getToAccount())) {
            transactions.add(transaction);
        } else {
            throw new InvalidObjectException();
        }
    }

    public Set<Transaction> getTransactions() {
        return Collections.unmodifiableSet(transactions);
    }
}
