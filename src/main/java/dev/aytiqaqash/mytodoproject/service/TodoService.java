package dev.aytiqaqash.mytodoproject.service;

import dev.aytiqaqash.mytodoproject.exception.ResourceNotFoundException;
import dev.aytiqaqash.mytodoproject.model.Todo;
import dev.aytiqaqash.mytodoproject.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }



    // Create

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // Read

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    // Update

    public Todo updateTodo(Long id, Todo todo) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setCompleted(todo.isCompleted());
        return todoRepository.save(existingTodo);
    }

    // Delete

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

}
