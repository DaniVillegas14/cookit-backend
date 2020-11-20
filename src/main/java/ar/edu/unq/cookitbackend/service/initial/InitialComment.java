package ar.edu.unq.cookitbackend.service.initial;

import ar.edu.unq.cookitbackend.model.Comment;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class InitialComment {

    @Autowired
    private UserRepository userRepository;

    public List<Comment> createComments() {
        User owner = User.builder()
                .email("dani.villegas@gmail.com")
                .name("Dani")
                .lastname("Villegas")
                .imageUrl("https://lh3.googleusercontent.com/-OauyH4e031Y/AAAAAAAAAAI/AAAAAAAAAAA/AMZuuclJRoo7o5Jv9Ty-1cOA_iIkp6nxgw/s96-c/photo.jpg")
                .isGoogleAccount(false)
                .build();

        userRepository.save(owner);

        return new ArrayList<>(Arrays.asList(
                Comment.builder()
                        .message("Muy buena receta ! :D")
                        .created_at(LocalDateTime.now())
                        .owner(owner)
                        .build(),
                Comment.builder()
                        .message("Pasame mas info")
                        .created_at(LocalDateTime.now())
                        .owner(owner)
                        .build(),
                Comment.builder()
                        .message("La hice pero no me salio :(")
                        .created_at(LocalDateTime.now())
                        .owner(owner)
                        .build(),
                Comment.builder()
                        .message("Muy buena tu receta, segui asi")
                        .created_at(LocalDateTime.now())
                        .owner(owner)
                        .build()
        ));
    }
}
