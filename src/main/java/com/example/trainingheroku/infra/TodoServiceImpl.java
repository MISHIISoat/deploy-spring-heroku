package com.example.trainingheroku.infra;

import com.example.trainingheroku.Todo;
import com.example.trainingheroku.TodoService;
import com.example.trainingheroku.exception.TodoNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoJpaRepository todoJpaRepository;
    private final DateUtility localDateUtility;

    public TodoServiceImpl(TodoJpaRepository todoJpaRepository, DateUtility localDateUtility) {
        this.todoJpaRepository = todoJpaRepository;
        this.localDateUtility = localDateUtility;
    }

    @Override
    public Todo create(Todo todo) {
        var todoToSave = Todo.TodoBuilder.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .createdDate(localDateUtility.now())
                .build();
        return todoJpaRepository.save(todoToSave);
    }

    @Override
    public List<Todo> findAll() {
        return todoJpaRepository.findAllOrderByCreatedDateDesc();
    }

    @Override
    public Todo findById(Long id) throws TodoNotFoundException {
        return todoJpaRepository.findById(id).orElseThrow(TodoNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) throws TodoNotFoundException {
        findById(id);
        todoJpaRepository.deleteById(id);
    }
}
