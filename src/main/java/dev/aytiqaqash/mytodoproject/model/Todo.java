package dev.aytiqaqash.mytodoproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Boolean completed;
}
