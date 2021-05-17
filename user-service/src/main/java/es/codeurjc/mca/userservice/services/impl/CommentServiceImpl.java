package es.codeurjc.mca.userservice.services.impl;

import es.codeurjc.mca.userservice.clients.CommentClient;
import es.codeurjc.mca.userservice.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentClient commentClient;

    @Override
    public boolean isInactiveUser(long userId) {
        return commentClient.getUserComments(userId).isEmpty();
    }
}
