package com.example.recipe_book_sever.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String category;

    private String image;

    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User creator;

    /**
     * 재료 리스트와 1:N 매핑
     * - Recipe가 주인
     * - cascade ALL: Recipe 저장/삭제 시 재료도 같이 저장/삭제
     * - orphanRemoval: 관계 끊어진 재료 엔티티 자동 삭제
     */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients=new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stepNumber ASC")
    private List<Instruction> instructions=new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites=new ArrayList<>();
 }
