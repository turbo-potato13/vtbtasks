package org.kortunov.vtbtasks.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    T save(T t);

    T update(T t);

    Optional<T> findById(ID identifier);

    List<T> findAll();
}
