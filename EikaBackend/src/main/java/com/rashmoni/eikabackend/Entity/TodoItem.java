package com.rashmoni.eikabackend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


public class TodoItem {

    @Getter
    @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String task_name;
        private Boolean isDone;

    }
