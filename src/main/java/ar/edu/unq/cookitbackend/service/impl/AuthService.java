package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.dto.request.LoginRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Session;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.SessionRepository;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import ar.edu.unq.cookitbackend.service.IAuthService;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void register(UserRequestDto request) throws NotFoundException {
        User user = userRepository.findByEmail(request.getEmail());

        if(user != null) {
            throw new NotFoundException("Usuario no encontrado");
        }

        userRepository.save(Converter.toUser(request));
    }

    @Override
    public void login(String token, LoginRequestDto request) {
        Session session = sessionRepository.findByToken(token);

        if (session == null) {
            User user = userRepository.findByEmail(request.getEmail());

            if (user == null) {
                createUserAndSession(token, request);
            } else {
                createSession(token, user);
            }
        }
    }

    private void createSession(String token, User user) {
        Session session = Session.builder()
                .token(token)
                .user(user)
                .build();

        sessionRepository.save(session);
    }

    private void createUserAndSession(String token, LoginRequestDto request) {
            User user = User.builder()
                    .name(request.getName())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .imageUrl(request.getImageUrl())
                    .build();

            Session session = Session.builder()
                                .token(token)
                                .user(user)
                                .build();

            userRepository.save(user);
            sessionRepository.save(session);
    }
}
