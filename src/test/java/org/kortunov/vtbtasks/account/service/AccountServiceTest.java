package org.kortunov.vtbtasks.account.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kortunov.vtbtasks.account.model.Account;
import org.kortunov.vtbtasks.account.model.Client;
import org.kortunov.vtbtasks.account.model.Transaction;
import org.kortunov.vtbtasks.repository.AccountRepository;
import org.kortunov.vtbtasks.repository.ClientRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCountMoneyInAmount(){
        final var currentDate = LocalDate.now();
        final var clients = initClients(currentDate);
        final var firstId = clients.first.getId();
        final var secondId = clients.second.getId();
        when(clientRepository.findById(firstId)).thenReturn(Optional.of(clients.first));
        when(clientRepository.findById(secondId)).thenReturn(Optional.of(clients.second));
        final var accountService = new AccountService(clientRepository, accountRepository);
        Assertions.assertEquals(1000, accountService.countMoneyInAmount(firstId, currentDate));
        Assertions.assertEquals(500, accountService.countMoneyInAmount(secondId, currentDate));
        Assertions.assertEquals(4000, accountService.countMoneyInAmount(firstId, currentDate.plus(5, DAYS)));
        verify(clientRepository, times(2)).findById(firstId);
        verify(clientRepository).findById(secondId);
    }

    private Pair<Client, Client> initClients(LocalDate currentDate) {
        final var client1 = Client.builder().build();
        final var client2 = Client.builder().build();
        final var account1 = new Account(client1);
        final var account2 = new Account(client2);
        client1.addAccount(account1);
        client2.addAccount(account2);
        final var transaction1 = Transaction.builder()
                .amount(1000)
                .date(currentDate)
                .toAccount(account1)
                .fromAccount(account2)
                .build();
        final var transaction2 = Transaction.builder()
                .amount(3000)
                .date(currentDate.plus(5, DAYS))
                .toAccount(account1)
                .fromAccount(account2)
                .build();
        final var transaction3 = Transaction.builder()
                .amount(500)
                .date(currentDate)
                .toAccount(account2)
                .fromAccount(account1)
                .build();
        account1.addTransaction(transaction1);
        account2.addTransaction(transaction1);
        account1.addTransaction(transaction2);
        account2.addTransaction(transaction2);
        account1.addTransaction(transaction3);
        account2.addTransaction(transaction3);
        return new Pair<>(client1, client2);
    }

//    @Test
//    public void testGetTransaction(){
//        final var currentDate = LocalDate.now();
//        final var clients = initClients(currentDate);
//        final var accounts = clients.
//        final var firstId = clients.first.getId();
//        final var secondId = clients.second.getId();
//        when(clientRepository.findById(firstId)).thenReturn(Optional.of(clients.first));
//        when(clientRepository.findById(secondId)).thenReturn(Optional.of(clients.second));
//
//    }

    @RequiredArgsConstructor
    private static class Pair<T1, T2> {
        private final T1 first;
        private final T2 second;
    }
}


