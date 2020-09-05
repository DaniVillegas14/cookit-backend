package ar.edu.unq.cookitbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="receta")
public class Recipe extends BaseEntity {

    @Column(name = "nombre", nullable = false)
    private String name;
    @Column(name = "descripcion", nullable = false)
    private String description;
    @Column(name = "imagen_url", nullable = false)
    private String imageUrl;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false, updatable = false)
//    private User user;

}
