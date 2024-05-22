package com.example.groceryorderapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MealSchedule {
    @Id
    @GeneratedValue
    private Long id;

    private String day;

    @OneToOne
    private Meal meal;

    public MealSchedule(String day, Meal meal) {
        this.day = day;
        this.meal = meal;
    }

    public MealSchedule(String day) {
        this.day = day;
    }
}
