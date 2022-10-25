package app.lab1.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
    Optional<E> findByID(K id);

    List<E> findAll();

    void saveNew(E entity);

    void deleteExisting(E entity);
}
