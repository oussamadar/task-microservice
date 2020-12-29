package com.javatechie.crud.example.Controller;

import com.javatechie.crud.example.entity.Todo;
import com.javatechie.crud.example.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService service;

    private final RestTemplate restTemplate;

    public TodoController(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    //check token
    public Integer checkToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String url = "http://eb8e384709b4.ngrok.io/api/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity req = new HttpEntity(headers);
//        return 1;
        ResponseEntity<Integer> resp = this.restTemplate.exchange(url, HttpMethod.GET, req, Integer.class);
        System.out.println(resp);
        if(resp.getStatusCode() == HttpStatus.OK) {
            return resp.getBody();
        } else {
            return -1;
        }
    }

    @PostMapping("/addTodo")
    public ResponseEntity addTodo(@RequestBody Todo todo, HttpServletRequest request){
        if(checkToken(request) == -1){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        else {
            return new ResponseEntity<>(service.saveTodo(todo), HttpStatus.OK);
        }
    }

    @GetMapping("/todosByUser/{userId}")
    public ResponseEntity<?> findTodoByUser(@PathVariable int userId, HttpServletRequest request){
            System.out.println("outside");
        if(checkToken(request) == -1){
            System.out.println("In -1");
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        else {
            System.out.println("In else");
            return new ResponseEntity<>(service.getUserTodo(userId), HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTodo(@RequestBody Todo todo, HttpServletRequest request){
        if(checkToken(request) == -1){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        else {
            return new ResponseEntity<>(service.updateTodo(todo), HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteTodoById(@PathVariable int id, HttpServletRequest request){
        if(checkToken(request) == -1){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        else {
            return new ResponseEntity<>(service.deleteTodo(id), HttpStatus.OK);
        }
    }

}
