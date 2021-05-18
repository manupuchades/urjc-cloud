package es.codeurjc.books.clients;

import es.codeurjc.books.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "user-service" ,url = "http://${user-service.host}:${user-service.port}/api/v1")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    Optional<User> findByNick(@RequestParam("nick") String nick);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    Optional<User> findById(@PathVariable("userId") long userId);
}
