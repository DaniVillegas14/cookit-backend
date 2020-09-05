package ar.edu.unq.cookitbackend.persistence;

import ar.edu.unq.cookitbackend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
