package com.example.jwtandwebsocket.dao;

import java.util.UUID;

public interface Dao<T> {

    T findById(UUID id);

    T save(T t);

    boolean deleteById(UUID id);
}
