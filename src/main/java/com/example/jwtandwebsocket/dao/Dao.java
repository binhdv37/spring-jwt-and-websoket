package com.example.jwtandwebsocket.dao;

import java.util.List;
import java.util.UUID;

public interface Dao<T> {

    T findById(UUID id);

    List<T> findAll();

    T save(T t);

    boolean deleteById(UUID id);
}
