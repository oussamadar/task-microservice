package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Todo;
import com.javatechie.crud.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    //get user specific todos
    public List<Todo> getUserTodo(int userId){
            return repository.findByUserId(userId);
    }

    //add todo
    public Todo saveTodo(Todo todo){
        return repository.save(todo);
    }

    //delete todo
    public String deleteTodo(int id) {
          repository.deleteById(id);
          return "product removed !!" + id;
      }

    //update todo as checked
    public Todo updateTodo(Todo todo){
        Todo existingTodo = repository.findById(todo.getId()).orElse(null);
        existingTodo.setUserId(todo.getUserId());
        existingTodo.setTitle(todo.getTitle());
        existingTodo.setChecked(todo.isChecked());
        return repository.save(existingTodo);
     }

}
