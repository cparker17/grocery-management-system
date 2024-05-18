package com.example.groceryorderapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "meals")
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private int numberOfIngredients;

    @ManyToMany
    private List<Ingredient> ingredients;

    @NotNull
    private String recipe;

    public Meal(Integer num) {
        numberOfIngredients = num;
        ingredients = new ArrayList<>();
        for(int i = 0; i < numberOfIngredients; i++) {
            ingredients.add(new Ingredient());
        }
    }
}
