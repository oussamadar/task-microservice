package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findByUserId(int userId);
}
