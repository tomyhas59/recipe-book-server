package com.example.recipe_book_sever.service.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDTO {
    private Long id;
    private RecipeDTO recipe;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeDTO {
        private Long id;
        private String name;
        private String description;
        private String category;
        private String image;
        private String content;
        private Long creatorId;
    }
}
