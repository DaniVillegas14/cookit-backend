package ar.edu.unq.cookitbackend.persistence;

import ar.edu.unq.cookitbackend.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByToken(String token);
}
