package com.example.groceryorderapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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

    @OneToMany
    private List<Ingredient> ingredients;
}
