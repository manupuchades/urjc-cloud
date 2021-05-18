package es.codeurjc.books.datasources;

import es.codeurjc.books.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.books.dtos.requests.UserRequestDto;
import es.codeurjc.books.dtos.responses.UserResponseDto;
import es.codeurjc.books.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserDatasource {

    List<User> findAll();

    Optional<User> findByNick(String nick);

    boolean existsByNick(String nick);

    User save(User user);

    Optional<User> findById(long userId);

    void delete(User user);

}
