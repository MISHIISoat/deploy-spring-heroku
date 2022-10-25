package com.example.trainingheroku;

import com.example.trainingheroku.exception.TodoNotFoundException;
import com.example.trainingheroku.infra.DateUtility;
import com.example.trainingheroku.infra.TodoJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TodoServiceImplIT {
    @Autowired
    TodoService todoService;

    @Autowired
    TodoJpaRepository todoJpaRepository;

    @MockBean
    DateUtility mockLocalDateUtility;

    @Test
    void test_create() {
        LocalDate today = LocalDate.of(2022, 10, 25);
        Mockito.when(mockLocalDateUtility.now()).thenReturn(today);
        var todoToCreate = Todo.TodoBuilder.builder()
                .title("a title")
                .content("new builder")
                .build();
        var newTodo = todoService.create(todoToCreate);

        assertThat(newTodo).isNotNull();
        assertThat(newTodo.getId()).isNotEqualTo(0L);
        assertThat(newTodo.getCreatedDate()).isEqualTo(today);
        var expectedTodo = todoJpaRepository.findById(newTodo.getId()).orElseThrow();
        assertThat(newTodo).isEqualTo(expectedTodo);
    }

    @Test
    void test_find_by_id() throws TodoNotFoundException {
        var todoToCreate = Todo.TodoBuilder.builder()
                .content("todo to find")
                .build();
        var newTodo = todoService.create(todoToCreate);

        var foundTodo = todoService.findById(newTodo.getId());

        assertThat(newTodo).isEqualTo(foundTodo);
    }

    @Test
    void test_find_all() {
        var todoToCreate1 = Todo.TodoBuilder.builder()
                .content("todo to add in list")
                .build();
        var todo1 = todoService.create(todoToCreate1);
        var todoToCreate2 = Todo.TodoBuilder.builder()
                .content("another todo")
                .build();
        var todo2 = todoService.create(todoToCreate2);

        var listTodo = todoService.findAll();


        assertThat(listTodo.size()).isEqualTo(2);
        assertThat(listTodo.contains(todo1)).isTrue();
        assertThat(listTodo.contains(todo2)).isTrue();
    }


    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void test_find_all_order_by_created_date() {
        Mockito.when(mockLocalDateUtility.now()).thenReturn(LocalDate.of(2021, 10, 11));
        var todoToCreate1 = Todo.TodoBuilder.builder()
                .title("title1")
                .content("todo to add in list")
                .build();
        var todo1 = todoService.create(todoToCreate1);
        Mockito.when(mockLocalDateUtility.now()).thenReturn(LocalDate.of(2020, 12, 10));
        var todoToCreate2 = Todo.TodoBuilder.builder()
                .title("title2")
                .content("another todo")
                .build();
        var todo2 = todoService.create(todoToCreate2);
        Mockito.when(mockLocalDateUtility.now()).thenReturn(LocalDate.of(2021, 10, 12));
        var todoToCreate3 = Todo.TodoBuilder.builder()
                .title("title3")
                .content("another todo 3")
                .build();
        var todo3 = todoService.create(todoToCreate2);

        var listTodo = todoService.findAll();


        assertThat(listTodo.size()).isEqualTo(3);
        assertThat(listTodo.get(0)).isEqualTo(todo3);
        assertThat(listTodo.get(1)).isEqualTo(todo1);
        assertThat(listTodo.get(2)).isEqualTo(todo2);
    }

    @Test
    void test_delete_todo() throws TodoNotFoundException {
        var todoToCreate = Todo.TodoBuilder.builder()
                .content("new builder")
                .build();
        var newTodo = todoService.create(todoToCreate);

        todoService.deleteById(newTodo.getId());

        var todo = todoJpaRepository.findById(newTodo.getId());
        assertThat(todo).isEmpty();
    }
}