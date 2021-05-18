package es.codeurjc.books.datasources.monolith;

import es.codeurjc.books.datasources.CommentDatasource;
import es.codeurjc.books.models.Comment;
import es.codeurjc.books.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@AllArgsConstructor
@Profile("monolith")
public class CommentDatasourceImpl implements CommentDatasource {

    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
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
