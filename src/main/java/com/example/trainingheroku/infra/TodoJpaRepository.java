package com.example.trainingheroku.infra;

import com.example.trainingheroku.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
    @Query("from Todo order by createdDate desc")
    List<Todo> findAllOrderByCreatedDateDesc();
}
