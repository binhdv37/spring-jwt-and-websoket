package com.example.jwtandwebsocket.dao;

import java.util.UUID;

public interface Dao<T> {

    T get(UUID id);

    T save(T t);

    boolean deleteById(UUID id);
}
