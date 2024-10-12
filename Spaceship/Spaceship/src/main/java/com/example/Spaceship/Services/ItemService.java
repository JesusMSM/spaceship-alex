package com.example.Spaceship.services;

import java.util.List;

import com.example.Spaceship.models.Item;

public class ItemService {

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
