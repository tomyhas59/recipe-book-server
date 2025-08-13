package com.example.recipe_book_sever.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String amount;

    /**
     * 레시피와 다대일(N:1) 관계 설정
     * - fetch.LAZY로 Recipe는 필요할 때만 조회하여 성능 최적화
     * - 외래키 컬럼명 recipe_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;
}