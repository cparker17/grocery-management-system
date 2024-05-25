package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.enums.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private Location location;

    @Transient
    private String locationString;

    public Ingredient (String description) {
        this.description = description;
    }

    public Ingredient (String description, Location location) {
        this.description = description;
        this.location = location;
    }

}
