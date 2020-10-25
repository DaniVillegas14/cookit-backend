package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.dto.request.LoginRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.JwtResponse;
import ar.edu.unq.cookitbackend.exception.EmailExistException;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import ar.edu.unq.cookitbackend.security.JwtTokenUtil;
import ar.edu.unq.cookitbackend.security.JwtUserDetailsService;
import ar.edu.unq.cookitbackend.service.IAuthService;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(UserRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());

        if(user != null) {
            throw new EmailExistException();
        }

        User newUser = Converter.toUser(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);
    }

    @Override
    public JwtResponse loginSocial(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            User newUser = User.builder()
                    .email(request.getEmail())
                    .name(request.getName())
                    .lastname(request.getLastname())
                    .imageUrl(request.getImageUrl())
                    .build();

            userRepository.save(newUser);

            return generateToken(newUser);
        }

        return generateToken(user);
    }

    private JwtResponse generateToken(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(user.getId(), user.getEmail(), token);
    }
}
