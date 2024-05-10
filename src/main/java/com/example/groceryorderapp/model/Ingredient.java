package com.example.groceryorderapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

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

    private Double quantity;

    private String quantityType;

    private String name;

}
