package org.kortunov.vtbtasks.role.model;

import lombok.*;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Builder
@ToString
@EqualsAndHashCode(of = {"name"})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Role {

    private static final AtomicLong ID_INCREMENTER = new AtomicLong();

    private final long id = ID_INCREMENTER.incrementAndGet();
    private final String name;
    @Singular("addPrivilege")
    private final Set<Privilege> privileges;
}
