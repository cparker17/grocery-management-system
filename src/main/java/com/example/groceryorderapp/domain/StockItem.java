package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.enums.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

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

    @NotFound
    private Location location;

    @Transient
    private String locationString;

}
