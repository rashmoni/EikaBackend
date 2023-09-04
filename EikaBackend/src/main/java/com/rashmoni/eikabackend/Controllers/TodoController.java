package com.rashmoni.eikabackend.Controllers;

import com.rashmoni.eikabackend.Payloads.ApiResponse;
import com.rashmoni.eikabackend.Payloads.TodoDto;
import com.rashmoni.eikabackend.Service.Impl.TodoServiceImpl;
import com.rashmoni.eikabackend.Service.TodoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping("/")
    public ResponseEntity<List<TodoDto>> getAllTodo() {
        return ResponseEntity.ok(this.todoService.getAllTodoItems());

    }
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getSingleTodo(@PathVariable Integer todoId){
        return ResponseEntity.ok(this.todoService.getTodoItemByID(todoId));
    }
    @PostMapping("/")
    public ResponseEntity<TodoDto> createTodo(){
        TodoDto createTodo = this.todoService.createTodoItem();
        return  new ResponseEntity<>(createTodo, HttpStatus.CREATED);
    }
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto>  updateTodoItem(@Valid @RequestBody TodoDto todoDto, @PathVariable("todoId") Integer tid){
        TodoDto UpdateUserDto = this.todoService.updateTodoItem(todoDto, tid);
        return ResponseEntity.ok(UpdateUserDto);
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<ApiResponse> deleteTodoItem(@PathVariable("todoId") Integer tid){
        todoService.deleteTodoItem(tid);
        return new ResponseEntity(new ApiResponse("ok",true), HttpStatus.OK);
    }


}
