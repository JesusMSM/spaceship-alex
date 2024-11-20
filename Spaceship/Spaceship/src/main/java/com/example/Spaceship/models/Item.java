package com.example.Spaceship.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double weight; // Weight in kg
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore  // Ignores this property in serialisation and helps to avoid infinite loops in requests.
    private Category category;
    
    public Item() {
    }

    public Item(Long id, String name, Double weight, String description, Category category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }


    /*Overrides the toString method to display the Item object's information in
    a readable format, useful for debugging or for displaying information
    in HTTP responses*/
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                ", category=" + (category != null ? category.getName() : "No category") +
                '}';
    }
    
}
