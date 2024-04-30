package com.example.groceryorderapp.model;

import com.example.groceryorderapp.domain.GroceryItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroceryOrder {

    private List<GroceryItem> groceryItemList;
}
