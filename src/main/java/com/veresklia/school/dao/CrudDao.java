package com.veresklia.school.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao <E, ID> {

    Integer save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    List<E> findAll(int page, int pageLimit);

    void update(E entity);

    void deleteById(ID id);
}
