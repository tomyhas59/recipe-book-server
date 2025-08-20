package com.example.recipe_book_sever.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
    private String content;
    private String image;


    @ManyToOne
    @JoinColumn(name = "creatorId")
    @JsonIgnoreProperties({"recipes", "favorites", "password"})
    private User creator;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"recipe", "user"})
    private List<Favorite> favorites = new ArrayList<>();

   @CreationTimestamp
    private LocalDateTime createdAt;
}
