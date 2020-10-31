package ar.edu.unq.cookitbackend.persistence;

import ar.edu.unq.cookitbackend.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                    "WHERE lower(r.name) LIKE lower(concat('%',:search,'%')) order by r.id DESC "
    )
    Page<Recipe> findAllBy(@Param("search") String search,
                           Pageable pageable);
    @Query(value = "SELECT r FROM Recipe r JOIN User u ON r.user.id = u.id INNER JOIN u.followers fw ON fw.id = :userId order by r.id DESC ")
    Page<Recipe> findFollowersRecipes(@Param ("userId") Long userId, Pageable pageable);
}