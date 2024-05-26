package com.example.groceryorderapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MealWrapper {
    private String name;
    private String ingredients;
    private String recipe;
}
