package com.example.groceryorderapp.model;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.domain.StockItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemsToOrder {
    List<Ingredient> ingredientsToOrder;
    List<StockItem> stockItemsToOrder;
}
