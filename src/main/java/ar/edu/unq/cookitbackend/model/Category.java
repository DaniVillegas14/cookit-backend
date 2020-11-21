package ar.edu.unq.cookitbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="categories")
public class Category extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonIgnore
    private User user;
    @ManyToMany
    @JoinTable(
            name = "rel_user_categories_favorites",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "recipe_id") }
    )
    @JsonIgnore
    private Set<Recipe> favoriteRecipeList;

    public void addFavoriteRecipes(Recipe favoriteRecipe) {
        this.favoriteRecipeList.add(favoriteRecipe);
    }

    public void removeFavoriteRecipes(Long id) {
        this.favoriteRecipeList.removeIf(favoriteRecipe -> favoriteRecipe.getId().equals(id));
    }
}
