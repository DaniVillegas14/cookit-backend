package ar.edu.unq.cookitbackend.persistence;

import ar.edu.unq.cookitbackend.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
            "SELECT c " +
                    "FROM Comment c " +
                    "WHERE c.recipe.id = :id " +
                    "ORDER BY c.created_at DESC"
    )
    List<Comment> findLastCommentByIdRecipe(Long id);

    @Query(
            "SELECT c " +
                    "FROM Comment c " +
                    "WHERE c.recipe.id = :id " +
                    "ORDER BY c.created_at"
    )
    Page<Comment> findAllBy(Long id, Pageable pageable);
}
