package com.rashmoni.eikabackend.Payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class TodoDto {
    private int id;

    private String task_name;
    private Boolean isDone;
}
