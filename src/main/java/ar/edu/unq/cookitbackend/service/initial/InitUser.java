package ar.edu.unq.cookitbackend.service.initial;

import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InitUser {
    @Autowired
    UserRepository userRepository;

    public User createUser () {
        User user = User.builder()
                .email("granchef@gmail.com")
                .lastname("Chef")
                .name("Gran")
                .build();
        userRepository.save(user);
        return user;
    }
}
