package org.kortunov.vtbtasks.role.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kortunov.vtbtasks.exception.InvalidObjectException;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum Privilege {
    READ(0L),
    WRITE(1L);

    @Getter
    private final long id;

    public static Privilege fromId(long id) {
       return Stream.of(values())
               .filter(e -> e.id == id)
               .findFirst()
               .orElseThrow(() -> new InvalidObjectException(String.format("Unknown id = %s", id)));
    }
}
