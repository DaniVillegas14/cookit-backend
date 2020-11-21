package ar.edu.unq.cookitbackend.persistence;

import ar.edu.unq.cookitbackend.model.Category;
import ar.edu.unq.cookitbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
