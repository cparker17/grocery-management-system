package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.model.Day;
import com.example.groceryorderapp.repositories.StockItemRepo;
import com.example.groceryorderapp.services.impl.MealScheduleFactory;
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
        MealScheduleFactory mealScheduleFactory = new MealScheduleFactory();
        mealList.add(mealScheduleFactory.createNewMealSchedule(Day.SUNDAY));
        mealList.add(mealScheduleFactory.createNewMealSchedule(Day.MONDAY));
        mealList.add(mealScheduleFactory.createNewMealSchedule(Day.TUESDAY));
        mealList.add(mealScheduleFactory.createNewMealSchedule(Day.WEDNESDAY));
        mealList.add(mealScheduleFactory.createNewMealSchedule(Day.THURSDAY));
        mealList.add(mealScheduleFactory.createNewMealSchedule(Day.FRIDAY));
        mealList.add(mealScheduleFactory.createNewMealSchedule(Day.SATURDAY));
    }
}
