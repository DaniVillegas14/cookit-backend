package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.request.LoginGoogleRequestDto;
import ar.edu.unq.cookitbackend.dto.request.LoginRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.JwtResponse;
import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;
import ar.edu.unq.cookitbackend.exception.EmailExistException;
import ar.edu.unq.cookitbackend.exception.LoginException;
import ar.edu.unq.cookitbackend.exception.NotFoundException;

import java.io.IOException;


public interface IAuthService {

    void register(UserRequestDto request) throws NotFoundException, EmailExistException, IOException, CreateDocumentationException;
    JwtResponse loginSocial(LoginGoogleRequestDto request);
    JwtResponse login(LoginRequestDto request) throws NotFoundException, LoginException;
}
