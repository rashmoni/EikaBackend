package com.rashmoni.eikabackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.rashmoni.eikabackend.Controllers.TodoController;
import com.rashmoni.eikabackend.Payloads.ApiResponse;
import com.rashmoni.eikabackend.Payloads.TodoDto;
import com.rashmoni.eikabackend.Service.Impl.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TodoControllerTest {

    private TodoController todoController;
    private TodoServiceImpl todoService;

    @BeforeEach
    public void setUp() {
        todoService = mock(TodoServiceImpl.class);
        todoController = new TodoController(todoService);
    }

    @Test
    public void testGetAllTodo() {
        // Arrange
        List<TodoDto> todoList = new ArrayList<>();
        todoList.add(new TodoDto(1, "Task 1"));
        todoList.add(new TodoDto(2, "Task 2"));

        when(todoService.getAllTodoItems()).thenReturn(todoList);

        // Act
        ResponseEntity<List<TodoDto>> response = todoController.getAllTodo();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todoList, response.getBody());
    }

    @Test
    public void testGetSingleTodo() {
        // Arrange
        int todoId = 1;
        TodoDto todoDto = new TodoDto(todoId, "Task 1");

        when(todoService.getTodoItemByID(todoId)).thenReturn(todoDto);

        // Act
        ResponseEntity<TodoDto> response = todoController.getSingleTodo(todoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todoDto, response.getBody());
    }

    @Test
    public void testCreateTodo() {
        // Arrange
        TodoDto createdTodo = new TodoDto(1, "New Task");

        when(todoService.createTodoItem()).thenReturn(createdTodo);

        // Act
        ResponseEntity<TodoDto> response = todoController.createTodo();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdTodo, response.getBody());
    }

    @Test
    public void testUpdateTodoItem() {
        // Arrange
        int todoId = 1;
        TodoDto updatedTodo = new TodoDto(todoId, "Updated Task");

        when(todoService.updateTodoItem(any(TodoDto.class), eq(todoId))).thenReturn(updatedTodo);

        // Act
        ResponseEntity<TodoDto> response = todoController.updateTodoItem(new TodoDto(todoId, "Task"), todoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTodo, response.getBody());
    }

    @Test
    public void testDeleteTodoItem() {
        // Arrange
        int todoId = 1;
        doNothing().when(todoService).deleteTodoItem(todoId);

        // Act
        ResponseEntity<ApiResponse> response = todoController.deleteTodoItem(todoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isSuccess());
    }
}

