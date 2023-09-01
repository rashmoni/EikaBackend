package com.rashmoni.eikabackend.repositories;

import com.rashmoni.eikabackend.Entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoItem, Integer> {
}
