package com.example.courtbooking.repo;

import com.example.courtbooking.model.DataWithId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> the underlying data type stored in the repo
 * @author Joshua Xing
 */
public class DataRepo<T extends DataWithId> {
    private long currentId = 0;
    @Getter
    private final List<T> data = new ArrayList<>();

    public synchronized long addData(T d) {
        d.setId(currentId);
        data.add(d);
        return currentId++;
    }

    public T getData(long id) {
        for (T d : data) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }
}
