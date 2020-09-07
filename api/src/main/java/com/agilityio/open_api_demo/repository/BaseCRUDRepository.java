package com.agilityio.open_api_demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseCRUDRepository<T> {
    Page<T> findAllWithPageable(Pageable pageable);
    T findById(String id);
    T save(T entity);
    void delete(T entity);
}
