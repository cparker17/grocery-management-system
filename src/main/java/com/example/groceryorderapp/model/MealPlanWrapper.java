package com.example.groceryorderapp.model;

import com.example.groceryorderapp.domain.Meal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MealPlanWrapper {
    private String sundayMeal;
    private String mondayMeal;
    private String tuesdayMeal;
    private String wednesdayMeal;
    private String thursdayMeal;
    private String fridayMeal;
    private String saturdayMeal;
}
