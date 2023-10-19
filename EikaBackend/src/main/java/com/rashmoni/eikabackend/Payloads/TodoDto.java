package com.rashmoni.eikabackend.Payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TodoDto {

    public TodoDto(int i, String s) {
        this.task_name = s;
        this.id = i;
    }
    private int id;
    private String task_name;
    private int price;
    private Boolean isDone;



}
