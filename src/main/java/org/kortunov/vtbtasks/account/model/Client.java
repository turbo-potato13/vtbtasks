package org.kortunov.vtbtasks.account.model;

import lombok.*;
import org.kortunov.vtbtasks.exception.InvalidObjectException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Builder
@ToString
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Client {
    private static final AtomicLong ID_INCREMENTER = new AtomicLong();

    private final long id = ID_INCREMENTER.incrementAndGet();
    private final String firstName;
    private final String lastName;
    private final Set<Account> accounts = new HashSet<>();

    public void addAccount(Account account) {
        if (this.equals(account.getClient())) {
            accounts.add(account);
        } else {
            throw new InvalidObjectException();
        }
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }
}
