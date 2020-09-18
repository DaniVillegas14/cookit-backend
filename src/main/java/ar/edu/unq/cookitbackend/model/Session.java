package ar.edu.unq.cookitbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="session")
public class Session extends BaseEntity {

    @Column(name = "Token", nullable = false)
    private String token;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

}
