package es.codeurjc.books.datasources.microservices;

import es.codeurjc.books.clients.UserClient;
import es.codeurjc.books.datasources.UserDatasource;
import es.codeurjc.books.models.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
@Profile("microservices")
public class UserDatasourceImpl implements UserDatasource {

    private static final Logger LOG = Logger.getLogger("UserDatasource");

    private UserClient userClient;

    @Override
    public List<User> findAll() {
        LOG.warning("Unexpected user datasource request : find all users");
        return null;
    }

    @Override
    public Optional<User> findByNick(String nick) {
        return userClient.findByNick(nick);
    }

    @Override
    public boolean existsByNick(String nick) {
        LOG.warning("Unexpected user datasource request : user exists by nick");
        return false;
    }

    @Override
    public User save(User user) {
        LOG.warning("Unexpected user datasource request : save user");
        return null;
    }

    @Override
    public Optional<User> findById(long userId) {
        return userClient.findById(userId);
    }

    @Override
    public void delete(User user) {
        LOG.warning("Unexpected user datasource request : delete user");
    }
}
