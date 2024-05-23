package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.model.Day;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Builder
@Entity
@AllArgsConstructor
@Data
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Meal> mealList;

    @Transient
    private Map<Day, Meal> mealSchedule;

    public MealPlan() {
        this.mealSchedule = new LinkedHashMap<>();
        for (Day day : Day.values()) {
            mealSchedule.put(day, new Meal());
        }
    }
}
