package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;

public interface IUserService {
    UserResponseDto getUserByToken(String token);
}
