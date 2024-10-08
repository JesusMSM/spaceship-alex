package com.example.Spaceship.Models;


public class Item{

   //Atributos de la clase Item

    private Long id;
    private String name;
    private Double peso;
    private Integer uds;
    
    //Constructor sin parámetros necesario para JPA
    public Item() {
    }

    //Constructor con parámetros completo
    public Item(Long id, String name, Double peso, Integer uds) {
        this.id = id;
        this.name = name;
        this.peso = peso;
        this.uds = uds;
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

    public Integer getUds() {
        return uds;
    }

    public void setUds(Integer uds) {
        this.uds = uds;
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
                ", uds=" + uds +
                '}';
    }
}