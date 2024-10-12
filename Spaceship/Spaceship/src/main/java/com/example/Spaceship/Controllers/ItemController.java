package com.example.Spaceship.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spaceship.Models.Item;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {
    //Define un endpoint HTTP GET que devuelve una lista de artículos (Item)
//en formato JSON cuando se accede a ese endpoint
	@GetMapping
        public List<Item> getItems() {
        return List.of(
          new Item(
                  1L,
                  "Machines",
                   150.50,
                    "Equipment"
		    )
		  );
    }
}
