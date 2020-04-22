package org.kortunov.vtbtasks.account.service;

import lombok.RequiredArgsConstructor;
import org.kortunov.vtbtasks.account.model.Transaction;
import org.kortunov.vtbtasks.account.model.TransactionHistory;
import org.kortunov.vtbtasks.repository.AccountRepository;
import org.kortunov.vtbtasks.repository.ClientRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class AccountService {
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public double countMoneyInAmount(long clientId, LocalDate date) {
        final var accounts = clientRepository.findById(clientId)
                .stream()
                .flatMap(client -> client.getAccounts().stream())
                .collect(toSet());
        final var dateToCheck = date.plus(1, ChronoUnit.DAYS);
        return accounts.stream()
                .flatMap(account -> account.getTransactions().stream())
                .filter(t -> accounts.contains(t.getToAccount()))
                .filter( t -> t.getDate().isBefore(dateToCheck))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public Set<TransactionHistory> getTransaction(long accountNumber){
        return accountRepository.findById(accountNumber)
                .stream()
                .flatMap(account -> account.getTransactions().stream())
                .map(t -> {
                    if (t.getToAccount().getAccountNumber() == accountNumber) {
                        return new TransactionHistory(t.getDate(), "crediting", t.getFromAccount(), t.getAmount());
                    } else {
                        return new TransactionHistory(t.getDate(), "debiting", t.getToAccount(), t.getAmount());
                    }
                })
                .collect(toSet());
    }
}
