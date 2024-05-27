package com.example.groceryorderapp;

import com.example.groceryorderapp.domain.*;
import com.example.groceryorderapp.enums.Location;
import com.example.groceryorderapp.model.MealWrapper;
import com.example.groceryorderapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
    RecipeInstructionRepo recipeInstructionRepo;

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    GroceryOrderRepo groceryOrderRepo;

    public static void main(String[] args) {
        SpringApplication.run(GroceryOrderAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(MealPlanRepo mealPlanRepo) {
        return (args) -> {
            if (mealRepo.findAll().isEmpty()) {
                try {
                   FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") +
                            "/src/main/resources/static/meal-data-load.xlsx"));
                    XSSFWorkbook workbook = new XSSFWorkbook(file);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    List<Meal> mealToPersist = new ArrayList<>();
                    ArrayList<String> rowData;
                    for (Row row : sheet) {
                        rowData = new ArrayList<>();
                        for (Cell cell : row) {
                            rowData.add(cell.getStringCellValue());
                        }
                        MealWrapper mealWrapper = MealWrapper.builder()
                                .name(rowData.get(0))
                                .recipe(rowData.get(1))
                                .ingredients(rowData.get(2))
                                .build();
                        String[] ingredientStrings = mealWrapper.getIngredients().split(",");
                        List<Ingredient> ingredients = new ArrayList<>();
                        for (String ingredient : ingredientStrings) {
                            String[] ingredientAndLocation = ingredient.split("-");
                            switch (ingredientAndLocation[1]) {
                                case "Freezer" ->
                                        ingredients.add(new Ingredient(ingredientAndLocation[0].toLowerCase().trim(),
                                                Location.FREEZER));
                                case "Refrigerator" ->
                                        ingredients.add(new Ingredient(ingredientAndLocation[0].toLowerCase().trim(),
                                                Location.REFRIGERATOR));
                                case "Cabinet" ->
                                        ingredients.add(new Ingredient(ingredientAndLocation[0].toLowerCase().trim(),
                                                Location.CABINET));
                            }
                        }
                        ingredientRepo.saveAll(ingredients);
                        String[] recipeStrings = mealWrapper.getRecipe().split("\\.");
                        List<RecipeInstruction> recipeInstructions = new ArrayList<>();
                        for (String recipeInstruction : recipeStrings) {
                            recipeInstructions.add(new RecipeInstruction(recipeInstruction.trim()));
                        }
                        recipeInstructionRepo.saveAll(recipeInstructions);

                        mealToPersist.add(Meal.builder()
                                .name(mealWrapper.getName())
                                .recipe(recipeInstructions)
                                .ingredients(ingredients)
                                .build());
                    }
                    mealRepo.saveAll(mealToPersist);
                    file.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (mealPlanRepo.findById(1L).isEmpty()) {
                MealPlan mealPlan = new MealPlan();
                mealPlan.setId(1L);
                mealPlanRepo.save(mealPlan);
            }
            if (groceryOrderRepo.findById(1L).isEmpty()) {
                GroceryOrder groceryOrder = new GroceryOrder();
                groceryOrder.setId(1L);
                groceryOrderRepo.save(groceryOrder);
            }
        };
    }
}
