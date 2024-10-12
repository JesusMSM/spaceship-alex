package com.example.Spaceship.models;


public class Item{

   //Atributos de la clase Item

    private Long id;
    private String name;
    private Double peso;
    private String description;
    
    //Constructor sin parámetros necesario para JPA
    public Item() {
    }

    //Constructor con parámetros completo
    public Item(Long id, String name, Double peso, String description) {
        this.id = id;
        this.name = name;
        this.peso = peso;
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
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
                ", peso=" + peso +
                ", description=" + description +
                '}';
    }
}