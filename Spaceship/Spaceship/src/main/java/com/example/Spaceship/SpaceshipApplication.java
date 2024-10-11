package com.example.Spaceship;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spaceship.Models.Item;

@SpringBootApplication
@RestController
public class SpaceshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceshipApplication.class, args);
	}
//Define un endpoint HTTP GET que devuelve una lista de artículos (Item)
//en formato JSON cuando se accede a ese endpoint
	@GetMapping
        public List<Item> hello() {
        return List.of(
          new Item(
                  1L,
                  "Maquinaria",
                   150.50,
                    "Equipment"
		    )
		  );
    }
}
