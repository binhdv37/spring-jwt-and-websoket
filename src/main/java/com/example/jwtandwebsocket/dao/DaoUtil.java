package com.example.jwtandwebsocket.dao;

import com.example.jwtandwebsocket.entity.ToData;

import java.util.Optional;

public abstract class DaoUtil {

    public static <T> T getData(ToData<T> data) {
        T object = null;
        if (data != null) {
            object = data.toData();
        }
        return object;
    }

    public static <T> T getData(Optional<? extends ToData<T>> data) {
        T object = null;
        if (data.isPresent()) {
            object = data.get().toData();
        }
        return object;
    }
}
