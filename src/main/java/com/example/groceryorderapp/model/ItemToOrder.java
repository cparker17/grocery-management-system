package com.example.groceryorderapp.model;

import com.example.groceryorderapp.enums.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ItemToOrder {
    private Location location;
    private String name;
}
