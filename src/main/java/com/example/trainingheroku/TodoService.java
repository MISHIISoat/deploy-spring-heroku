package com.example.trainingheroku;

import com.example.trainingheroku.exception.TodoNotFoundException;

import java.util.List;

public interface TodoService {
    Todo create(Todo todo);

    List<Todo> findAll();

    Todo findById(Long id) throws TodoNotFoundException;

    void deleteById(Long id) throws TodoNotFoundException;
}
