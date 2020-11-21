package ar.edu.unq.cookitbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="usuario")
public class User extends BaseEntity {

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "imageurl")
    private String imageUrl;
    @Column(name = "isGoogleAccount", nullable = false)
    private Boolean isGoogleAccount;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @Builder.Default
    private List<Recipe> recipes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @Builder.Default
    private List<Category> categories_recipes = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "rel_user_recipe",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "recipe_id") }
    )
    private Set<Recipe> favorite_recipes;
    @ManyToMany
    @JoinTable(
            name = "rel_user_follow",
            joinColumns = { @JoinColumn(name = "user_follow_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_follower_id")}
    )
    private Set<User> follows;

    @ManyToMany(mappedBy = "follows")
    private Set<User> followers;

    public void addFollow(User user) {
        this.follows.add(user);
    }
    public void addFavoriteRecipes(Recipe recipe) { this.favorite_recipes.add(recipe); }
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void removeFollow(User user) {
        this.follows.removeIf(u -> u.getId().equals(user.getId()));
    }
    public void removeRecipeToFavorite(Recipe recipe) {
        this.favorite_recipes.removeIf(r -> r.getId().equals(recipe.getId()));
        this.categories_recipes.forEach(category -> category.removeFavoriteRecipes(recipe.getId()));
    }

    public void addCategory(Category category) {
        this.categories_recipes.add(category);
    }
}
