package ar.edu.unq.cookitbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="user")
public class User extends BaseEntity {

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "nombre", nullable = false)
    private String name;
    @Column(name = "apellido", nullable = false)
    private String lastname;
    @Column(name = "imagen_url")
    private String imageUrl;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Recipe> recipes;
}
