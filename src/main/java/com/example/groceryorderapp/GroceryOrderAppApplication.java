package com.example.groceryorderapp;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.domain.RecipeInstruction;
import com.example.groceryorderapp.model.MealWrapper;
import com.example.groceryorderapp.repositories.IngredientRepo;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.repositories.RecipeInstructionRepo;
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
    RecipeInstructionRepo recipeInstructionRepo;

    public static void main(String[] args) {
        SpringApplication.run(GroceryOrderAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData() {
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
                            ingredients.add(new Ingredient(ingredient));
                        }
                        ingredientRepo.saveAll(ingredients);
                        String[] recipeStrings = mealWrapper.getRecipe().split("\\.");
                        List<RecipeInstruction> recipeInstructions = new ArrayList<>();
                        for (String recipeInstruction : recipeStrings) {
                            recipeInstructions.add(new RecipeInstruction(recipeInstruction));
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
        };
    }
}
