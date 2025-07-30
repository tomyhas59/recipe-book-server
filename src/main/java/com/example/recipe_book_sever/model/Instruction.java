package com.example.recipe_book_sever.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instruction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stepNumber;

    private String description;

    /**
     * 레시피와 다대일(N:1) 관계 설정
     * - 지연 로딩(fetch.LAZY) 적용
     * - 외래키 컬럼명 recipe_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;
}
