package com.example.trainingheroku.app;

import com.example.trainingheroku.Todo;

public class TodoDtoMapper {
    static TodoDto to (Todo todo) {
        return TodoDto.TodoDtoBuilder.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .createdDate(todo.getCreatedDate())
                .build();
    }
}
