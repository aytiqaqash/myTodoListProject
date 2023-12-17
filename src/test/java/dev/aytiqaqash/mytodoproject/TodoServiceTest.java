package dev.aytiqaqash.mytodoproject;

import dev.aytiqaqash.mytodoproject.model.Todo;
import dev.aytiqaqash.mytodoproject.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {
    @Autowired
    private TodoService todoService;

    @Test
    public void testGetAllTodos() {

        // Create 10 new Todo objects
        for (int i = 1; i <= 100; i++) {
            Todo todo = new Todo();
            todo.setTitle("Todo " + i);
            todo.setDescription("This is Todo " + i);
            todo.setCompleted(false);
            todoService.createTodo(todo);
        }

        // Create a new PageRequest object
        Pageable pageable = PageRequest.of(0, 5);

        Page<Todo> todos = todoService.getAllTodos(pageable);
        assertNotNull(todos);

        // Assert that the result has the correct number of elements
        assertEquals(5, todos.getNumberOfElements());

        // Assert that the result has the correct total number of elements
        assertEquals(10, todos.getTotalElements());

        // Assert that the result has the correct number of pages
        assertEquals(2, todos.getTotalPages());

        // Assert that the result has the correct content
        List<Todo> todoList = todos.getContent();
        assertEquals("Todo 1", todoList.get(0).getTitle());
        assertEquals("Todo 2", todoList.get(1).getTitle());
        assertEquals("Todo 3", todoList.get(2).getTitle());
        assertEquals("Todo 4", todoList.get(3).getTitle());
        assertEquals("Todo 5", todoList.get(4).getTitle());
    }

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("This is a test todo");
        todo.setCompleted(false);

        Todo createdTodo = todoService.createTodo(todo);
        assertNotNull(createdTodo.getId());
    }

    @Test
    public void testUpdateTodo() {
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("This is a test todo");
        todo.setCompleted(false);

        Todo createdTodo = todoService.createTodo(todo);
        assertNotNull(createdTodo.getId());

        createdTodo.setTitle("Updated Test Todo");
        Todo updatedTodo = todoService.updateTodo(createdTodo.getId(), createdTodo);
        assertEquals("Updated Test Todo", updatedTodo.getTitle());
    }

    @Test
    public void testDeleteTodoById() {
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("This is a test todo");
        todo.setCompleted(false);

        Todo createdTodo = todoService.createTodo(todo);
        assertNotNull(createdTodo.getId());

        todoService.deleteTodoById(createdTodo.getId());

        // Create a new PageRequest object
        Pageable pageable = PageRequest.of(0, 5);

        List<Todo> todos = (List<Todo>) todoService.getAllTodos(pageable);
        assertFalse(todos.contains(createdTodo));
    }
}
