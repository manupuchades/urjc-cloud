package es.codeurjc.mca.userservice.services.impl;

import es.codeurjc.mca.userservice.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.mca.userservice.dtos.requests.UserRequestDto;
import es.codeurjc.mca.userservice.dtos.responses.UserResponseDto;
import es.codeurjc.mca.userservice.exceptions.UserCanNotBeDeletedException;
import es.codeurjc.mca.userservice.exceptions.UserNotFoundException;
import es.codeurjc.mca.userservice.exceptions.UserWithSameNickException;
import es.codeurjc.mca.userservice.models.User;
import es.codeurjc.mca.userservice.repositories.UserRepository;
import es.codeurjc.mca.userservice.services.CommentService;
import es.codeurjc.mca.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private CommentService commentService;
    private UserRepository userRepository;

    public Collection<UserResponseDto> findAll() {
        return this.userRepository.findAll().stream()
                .map(user -> this.mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        if (this.userRepository.existsByNick(userRequestDto.getNick())) {
            throw new UserWithSameNickException();
        }
        User user = this.mapper.map(userRequestDto, User.class);
        user = this.userRepository.save(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto findById(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto findByNick(String nick) {
        User user = this.userRepository.findByNick(nick).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equalsIgnoreCase(updateUserEmailRequestDto.getEmail())) {
            user.setEmail(updateUserEmailRequestDto.getEmail());
            user = this.userRepository.save(user);
        }
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto delete(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!commentService.isInactiveUser(userId)) {
            throw new UserCanNotBeDeletedException();
        }
        this.userRepository.delete(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

}
