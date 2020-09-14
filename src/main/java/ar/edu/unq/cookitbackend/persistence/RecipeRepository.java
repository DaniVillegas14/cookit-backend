package ar.edu.unq.cookitbackend.persistence;

import ar.edu.unq.cookitbackend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(
            "SELECT r " +
                    "FROM Recipe r " +
                    "WHERE lower(r.name) LIKE lower(concat('%',:search,'%'))"
    )
    List<Recipe> findRecipesByQuery(@Param("search") String query);
}
