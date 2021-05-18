package es.codeurjc.books.datasources;

import es.codeurjc.books.dtos.requests.CommentRequestDto;
import es.codeurjc.books.dtos.responses.CommentResponseDto;
import es.codeurjc.books.dtos.responses.UserCommentResponseDto;
import es.codeurjc.books.models.Comment;

import java.util.Collection;
import java.util.Optional;

public interface CommentDatasource {

    Comment save(Comment comment);

    void delete(Comment comment);

    Optional<Comment> findByBookIdAndId(Long bookId, Long commentId);

    Collection<Comment> findByUserId(long userId);

}
