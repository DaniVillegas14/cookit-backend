package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.request.LoginRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.JwtResponse;
import ar.edu.unq.cookitbackend.exception.NotFoundException;

public interface IAuthService {

    void register(UserRequestDto request) throws NotFoundException;
    JwtResponse loginSocial(LoginRequestDto request);
}
