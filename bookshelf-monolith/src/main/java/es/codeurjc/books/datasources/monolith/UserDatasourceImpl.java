package es.codeurjc.books.datasources.monolith;

import es.codeurjc.books.datasources.UserDatasource;
import es.codeurjc.books.models.User;
import es.codeurjc.books.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Profile("monolith")
public class UserDatasourceImpl implements UserDatasource {

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByNick(String nick) {
        return userRepository.findByNick(nick);
    }

    @Override
    public boolean existsByNick(String nick) {
        return userRepository.existsByNick(nick);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
