package ar.edu.unq.cookitbackend.persistence;

import ar.edu.unq.cookitbackend.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(
            "SELECT r " +
                    "FROM Recipe r " +
                    "WHERE r.available = true " +
                    "and lower(r.name) LIKE lower(concat('%',:search,'%')) order by r.id DESC "
    )
    Page<Recipe> findAllBy(@Param("search") String search,
                           Pageable pageable);

    @Query(value = "SELECT r " +
                        "FROM Recipe r JOIN User u ON r.user.id = u.id AND r.available = true " +
                        "INNER JOIN u.followers fw ON fw.id = :userId " +
                        "order by r.id DESC")
    Page<Recipe> findFollowersRecipes(@Param("userId") Long userId, Pageable pageable);

    @Query(
            "SELECT r " +
                    "FROM Recipe r " +
                    "WHERE r.id = :id " +
                    "AND r.available = true"
    )
    Optional<Recipe> findByIdRecipe(Long id);

    @Query(
            "SELECT r " +
                    "FROM Recipe r " +
                    "WHERE r.user.id = :userId AND r.available = true"
    )
    List<Recipe> findRecipesByIdUser(@Param("userId") Long userId);

    @Query(
            "SELECT r " +
                    "FROM Recipe r JOIN User u ON r.user.id = u.id AND r.available = true " +
                    "INNER JOIN u.favorite_recipes f ON f.user.id = :userId"
    )
    List<Recipe> findRecipesFavoritesByIdUser(@Param("userId") Long userId);
}