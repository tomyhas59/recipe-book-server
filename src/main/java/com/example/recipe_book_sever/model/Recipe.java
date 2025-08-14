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

    private String name;          // 레시피 이름
    private String description;   // 간단 설명
    private String category;      // 카테고리
    private String image;         // 이미지 URL
    private String content;       // 재료 + 조리법 전체

    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User creator;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites = new ArrayList<>();
}
