package com.javatechie.crud.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Todo_List")
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private String description;
    private String title;
    private boolean checked;
}
