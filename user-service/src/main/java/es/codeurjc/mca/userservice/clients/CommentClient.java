package es.codeurjc.mca.userservice.clients;

import es.codeurjc.mca.userservice.dtos.responses.UserCommentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@FeignClient(name = "bookshelf-monolith" ,url = "http://${bookshelf-monolith.host}:${bookshelf-monolith.port}/api/v1")
public interface CommentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/comments")
    Collection<UserCommentResponseDto> getUserComments(@PathVariable("userId") long userId);
}
