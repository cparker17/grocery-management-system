package com.example.groceryorderapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealPlan {
    @Id
    private Long id;

    @ManyToMany
    private List<Meal> meals;
}
