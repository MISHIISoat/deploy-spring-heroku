package com.example.trainingheroku.app;

import java.time.LocalDate;

public class TodoDto {
    private Long id = 0L;
    private String title;
    private String content;
    private LocalDate createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }


    public static final class TodoDtoBuilder {
        private Long id;
        private String title;
        private String content;
        private LocalDate createdDate;

        private TodoDtoBuilder() {
        }

        public static TodoDtoBuilder builder() {
            return new TodoDtoBuilder();
        }

        public TodoDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TodoDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TodoDtoBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TodoDtoBuilder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public TodoDto build() {
            TodoDto todoDto = new TodoDto();
            todoDto.setId(id);
            todoDto.setTitle(title);
            todoDto.setContent(content);
            todoDto.setCreatedDate(createdDate);
            return todoDto;
        }
    }
}