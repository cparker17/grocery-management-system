package com.example.groceryorderapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.persistence.Table;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_items")
@Data
public class StockItem implements GroceryItem{

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String type;
}
