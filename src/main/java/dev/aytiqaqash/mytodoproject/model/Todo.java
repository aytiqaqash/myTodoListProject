package dev.aytiqaqash.mytodoproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "todos")
@Validated
public class Todo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    String title;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    String description;

    @NotNull
    @Column(nullable = false)
    Boolean completed;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isCompleted() {
        return completed;
    }


}
