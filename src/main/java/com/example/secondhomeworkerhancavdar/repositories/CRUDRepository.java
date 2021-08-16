package com.example.secondhomeworkerhancavdar.repositories;

import java.util.List;

public interface CRUDRepository <T>{
    List<T> findAll();
    T findById(long id);
    T save(T t);
    void delete(long id);
    void update (T t, long id);
}
