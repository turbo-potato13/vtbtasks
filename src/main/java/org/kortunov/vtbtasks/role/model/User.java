package org.kortunov.vtbtasks.role.model;

import lombok.*;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Builder
@ToString
@EqualsAndHashCode(of = {"email"})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private static final AtomicLong ID_INCREMENTER = new AtomicLong();

    private final long id = ID_INCREMENTER.incrementAndGet();
    private final String firstName;
    private final String lastName;
    private final String email;
    @Singular("addRole")
    private final Set<Role> roles;
}
