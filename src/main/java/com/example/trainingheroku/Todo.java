package com.example.trainingheroku;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private LocalDate createdDate;

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public static final class TodoBuilder {
        private Long id;
        private String title;
        private String content;
        private LocalDate createdDate;

        private TodoBuilder() {
        }

        public static TodoBuilder builder() {
            return new TodoBuilder();
        }

        public TodoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TodoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TodoBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TodoBuilder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Todo build() {
            Todo todo = new Todo();
            todo.title = this.title;
            todo.createdDate = this.createdDate;
            todo.id = this.id;
            todo.content = this.content;
            return todo;
        }
    }
}
