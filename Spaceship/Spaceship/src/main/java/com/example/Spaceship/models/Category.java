package com.example.Spaceship.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long priority;

    @OneToMany(mappedBy = "category")
    @JsonIgnore // Ignores this property in serialisation and helps to avoid infinite loops in requests.
    private List<Item> items;

    public Category (){

    }

    public Category(Long id, String name, String description, Long priority){
    this.id = id;
    this.name = name;
    this.description = description;
    this.priority = priority;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Long getPriority(){
        return priority;
    }

    public void setPriority(Long priority){
        this.priority = priority;
    }

    public List<Item> getItems(){
        return items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}
