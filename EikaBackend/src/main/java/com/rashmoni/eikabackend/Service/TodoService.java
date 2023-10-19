package com.rashmoni.eikabackend.Service;

import com.rashmoni.eikabackend.Payloads.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createTodoItem();
    TodoDto updateTodoItem(TodoDto todoDto,Integer todoId);
    TodoDto getTodoItemByID(Integer todoId);
    List<TodoDto> getAllTodoItems();
    void deleteTodoItem(Integer todoId);


}
