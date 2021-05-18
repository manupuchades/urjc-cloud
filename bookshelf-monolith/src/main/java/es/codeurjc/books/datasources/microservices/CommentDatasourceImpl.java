package es.codeurjc.books.datasources.microservices;

import es.codeurjc.books.datasources.CommentDatasource;
import es.codeurjc.books.models.Comment;
import es.codeurjc.books.repositories.CommentRepository;
import es.codeurjc.books.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@AllArgsConstructor
@Profile("microservices")
public class CommentDatasourceImpl implements CommentDatasource {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Override
    public Comment save(Comment comment) {
        userRepository.save(comment.getUser());
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Optional<Comment> findByBookIdAndId(Long bookId, Long commentId) {
        return commentRepository.findByBookIdAndId(bookId, commentId);
    }

    @Override
    public Collection<Comment> findByUserId(long userId) {
        return commentRepository.findByUserId(userId);
    }
}
