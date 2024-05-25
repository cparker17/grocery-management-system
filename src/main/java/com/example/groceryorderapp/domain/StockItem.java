package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.enums.Location;
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
@Table(name = "stock_items")
public class StockItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private Location location;

}
