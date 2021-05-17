package es.codeurjc.mca.userservice.services;

import es.codeurjc.mca.userservice.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.mca.userservice.dtos.requests.UserRequestDto;
import es.codeurjc.mca.userservice.dtos.responses.UserResponseDto;

import java.util.Collection;

public interface UserService {

    Collection<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto findById(long userId);

    UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto);

    UserResponseDto delete(long userId);

}
