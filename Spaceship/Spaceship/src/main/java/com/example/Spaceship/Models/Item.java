package com.example.Spaceship.models;


public class Item{

   //Atributos de la clase Item

    private Long id;
    private String name;
    private Double weight;
    private String description;
    
    //Constructor sin parámetros necesario para JPA
    public Item() {
    }

    //Constructor con parámetros completo
    public Item(Long id, String name, Double weight, String description) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.description = description;
    }

    //Getters y Setters
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

    public Double getweight() {
        return weight;
    }

    public void setweight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescrption(String description) {
        this.description = description;
    }
    //Sobreescribe el método toString para mostrar la información del objeto Item
    //en un formato legible, útil para depuración o para mostrar información
    //en las respuestas HTTP
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", peso=" + weight +
                ", description=" + description +
                '}';
    }
}