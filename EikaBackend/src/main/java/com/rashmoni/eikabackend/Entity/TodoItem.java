package com.rashmoni.eikabackend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="todos")
@NoArgsConstructor
@Getter
@Setter
public class TodoItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String task_name;
        private int price;
        private Boolean isDone;

    }
