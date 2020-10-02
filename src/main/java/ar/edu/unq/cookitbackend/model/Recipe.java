package ar.edu.unq.cookitbackend.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="recipe")
public class Recipe extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "descripton", nullable = false)
    private String description;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "comensales", nullable = false)
    private int comensales;
    @Column(name = "time", nullable = false)
    private int time;
    @CreationTimestamp
    private LocalDateTime created_at;
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Step> steps;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false, updatable = false)
//    private User user;
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
}
