package ar.edu.unq.cookitbackend.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="comment")
public class Comment extends BaseEntity {

    @Column(name = "message", nullable = false)
    private String message;
    @CreationTimestamp
    private LocalDateTime created_at;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false, updatable = false)
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;
}
