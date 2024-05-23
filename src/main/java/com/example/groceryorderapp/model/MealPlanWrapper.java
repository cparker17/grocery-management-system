package com.example.groceryorderapp.model;

import com.example.groceryorderapp.domain.Meal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@Data
@Builder
public class MealPlanWrapper {
    private Map<Day, Meal> mealSchedule;

    public MealPlanWrapper() {
        this.mealSchedule = new LinkedHashMap<>();
        for (Day day : Day.values()) {
            mealSchedule.put(day, new Meal());
        }
    }
}
