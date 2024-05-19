package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.model.Day;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<MealSchedule> mealList;

    public MealPlan(List<MealSchedule> mealList) {
        this.mealList = mealList;
        mealList.add(new MealSchedule(Day.SUNDAY.name(), new Meal()));
        mealList.add(new MealSchedule(Day.MONDAY.name(), new Meal()));
        mealList.add(new MealSchedule(Day.TUESDAY.name(), new Meal()));
        mealList.add(new MealSchedule(Day.WEDNESDAY.name(), new Meal()));
        mealList.add(new MealSchedule(Day.THURSDAY.name(), new Meal()));
        mealList.add(new MealSchedule(Day.FRIDAY.name(), new Meal()));
        mealList.add(new MealSchedule(Day.SATURDAY.name(), new Meal()));
    }
}
