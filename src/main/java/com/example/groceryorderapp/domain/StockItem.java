package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.model.GroceryItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_items")
public class StockItem implements GroceryItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;
}
