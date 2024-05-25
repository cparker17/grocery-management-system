package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.RecipeInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeInstructionRepo extends JpaRepository<RecipeInstruction, Long> {
}
