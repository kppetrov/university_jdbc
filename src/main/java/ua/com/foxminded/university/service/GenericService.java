package ua.com.foxminded.university.service;

import java.util.List;

public interface GenericService<T> {
    List<T> getAll();
    T getById(int id);
    T insert(T item);
    int update(T item);
    int delete(int id);
}
