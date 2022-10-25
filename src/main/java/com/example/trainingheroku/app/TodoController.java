package com.example.trainingheroku.app;

import com.example.trainingheroku.Todo;
import com.example.trainingheroku.TodoService;
import com.example.trainingheroku.exception.TodoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    ResponseEntity<URI> createTodo(@RequestBody TodoDto todoDto) {
        log.info("create todo with title '{}' and content '{}'", todoDto.getTitle(), todoDto.getContent());
        var todo = Todo.TodoBuilder.builder()
                .title(todoDto.getTitle())
                .content(todoDto.getContent())
                .build();
        var newTodo = todoService.create(todo);

        log.info("new todo created : {}", newTodo);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(newTodo.getId())
                .toUri();
        return created(location).build();
    }

    @GetMapping
    ResponseEntity<List<TodoDto>> getAll() {
        log.info("get all todos");
        var todoListDto = todoService.findAll()
                .stream().map(TodoDtoMapper::to)
                .toList();
        return ok(todoListDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<TodoDto> getById(@PathVariable("id") Long todoId) throws TodoNotFoundException {
        log.info("get by id");
        var todoDto = TodoDtoMapper.to(todoService.findById(todoId));
        return ResponseEntity.ok(todoDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Long todoId) throws TodoNotFoundException {
        todoService.deleteById(todoId);
        return noContent().build();
    }
}
