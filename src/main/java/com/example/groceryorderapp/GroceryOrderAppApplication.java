package com.example.groceryorderapp;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.domain.MealSchedule;
import com.example.groceryorderapp.repositories.IngredientRepo;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.repositories.MealScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

@SpringBootApplication
public class GroceryOrderAppApplication {

    @Autowired
    MealRepo mealRepo;

    @Autowired
    IngredientRepo ingredientRepo;

    @Autowired
    MealPlanRepo mealPlanRepo;

    public static void main(String[] args) {
        SpringApplication.run(GroceryOrderAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData() {
        return (args) -> {

            if (mealPlanRepo.findById(1L).isEmpty()) {
                MealPlan mealPlan = new MealPlan(new ArrayList<>());
                mealPlanRepo.save(mealPlan);
            }

            if (mealRepo.findAll().isEmpty()) {
                try {
                    // *** TEST DATA ***
                    Ingredient ingredient1 = new Ingredient("2 cups of this ingredient");
                    ingredientRepo.save(ingredient1);

                    Ingredient ingredient2 = new Ingredient("1 tsp of this ingredient");
                    ingredientRepo.save(ingredient2);

                    Ingredient ingredient3 = new Ingredient("1/4 cup of this ingredient");
                    ingredientRepo.save(ingredient3);

                    Ingredient ingredient4 = new Ingredient("2 tbsp of this ingredient");
                    ingredientRepo.save(ingredient4);

                    Ingredient ingredient5 = new Ingredient("3 oz of this ingredient");
                    ingredientRepo.save(ingredient5);

                    FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") +
                            "/src/main/resources/static/meal-sample-data-test.xlsx"));
                    XSSFWorkbook workbook = new XSSFWorkbook(file);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    List<Meal> mealToPersist = new ArrayList<>();
                    ArrayList<String> rowData;
                    for (Row row : sheet) {
                        rowData = new ArrayList<>();
                        for (Cell cell : row) {
                            rowData.add(cell.getStringCellValue());
                        }
                        Meal meal = Meal.builder()
                                .name(rowData.get(0))
                                .recipe(rowData.get(1))
                                .ingredients(List.of(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5))
                                .build();
                        mealToPersist.add(meal);
                    }
                    mealRepo.saveAll(mealToPersist);
                    file.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
