package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructionRepository extends JpaRepository<Instruction,Long> {
}
