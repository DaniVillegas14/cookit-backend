package ar.edu.unq.cookitbackend.security;

import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw  new UsernameNotFoundException("User not found with email: " + email);
        }

        // TODO verificar esto cuando agreguemos el register
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                getPasswordOrNothing(""), Collections.emptyList());
    }

    private String getPasswordOrNothing(String password) {
        if(password != null) {
            return password;
        }
        return "Nothing";
    }
}