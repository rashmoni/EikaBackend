package com.rashmoni.eikabackend.Service.Impl;

import com.rashmoni.eikabackend.Entity.TodoItem;
import com.rashmoni.eikabackend.Exceptions.ResourceNotFoundException;
import com.rashmoni.eikabackend.Payloads.TodoDto;
import com.rashmoni.eikabackend.Service.TodoService;
import com.rashmoni.eikabackend.repositories.TodoRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TodoRepo todoRepo;

    @Override
    public TodoDto createTodoItem() {

        int taskPredicate = todoRepo.findAll().size()+1;
        TodoDto newTodoDto = new TodoDto();
        newTodoDto.setTask_name("Shopping Item #" +  taskPredicate);
        newTodoDto.setPrice(0);
        newTodoDto.setIsDone(false);
        TodoItem todoItem = this.dtoToTodoItem(newTodoDto);
        TodoItem savedTodoItem = this.todoRepo.save(todoItem);
        return this.TodoItemToDto(savedTodoItem);
    }

    @Override
    public TodoDto updateTodoItem(TodoDto todoDto, Integer todoId) {
        TodoItem todoItem = this.todoRepo.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("TodoItem", "Id", todoId));
        todoItem.setTask_name(todoDto.getTask_name());
        todoItem.setIsDone(todoDto.getIsDone());
        TodoItem updateTodoItem = this.todoRepo.save(todoItem);
        TodoDto todoDto1 = this.TodoItemToDto(updateTodoItem);
        return todoDto1;
    }





    @Override
    public TodoDto getTodoItemByID(Integer todoId) {
        TodoItem todoItem = this.todoRepo.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("TodoItem", "Id", todoId));
        return this.TodoItemToDto(todoItem);

    }

    @Override
    public List<TodoDto> getAllTodoItems() {
        List<TodoItem> todoItems = this.todoRepo.findAll();
        List<TodoDto> todoDtos = todoItems.stream().map(todoItem -> this.TodoItemToDto(todoItem)).collect(Collectors.toList());
        return todoDtos;
    }



    @Override
    public void deleteTodoItem(Integer todoId) {
        TodoItem todoItem = this.todoRepo.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("TodoItem", "Id", todoId));
        this.todoRepo.delete(todoItem);
    }

    private TodoItem dtoToTodoItem(TodoDto todoDto) {
        TodoItem todoItem = this.modelMapper.map(todoDto,TodoItem.class);
        return todoItem;
    }

    public TodoDto TodoItemToDto(TodoItem todoItem) {
        TodoDto todoDto= this.modelMapper.map(todoItem,TodoDto.class);
        return todoDto;
    }
}
