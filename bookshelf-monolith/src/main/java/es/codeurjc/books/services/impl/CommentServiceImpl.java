package es.codeurjc.books.services.impl;

import es.codeurjc.books.datasources.BookDatasource;
import es.codeurjc.books.datasources.CommentDatasource;
import es.codeurjc.books.datasources.UserDatasource;
import es.codeurjc.books.dtos.requests.CommentRequestDto;
import es.codeurjc.books.dtos.responses.CommentResponseDto;
import es.codeurjc.books.dtos.responses.UserCommentResponseDto;
import es.codeurjc.books.exceptions.BookNotFoundException;
import es.codeurjc.books.exceptions.CommentNotFoundException;
import es.codeurjc.books.exceptions.UserNotFoundException;
import es.codeurjc.books.models.Book;
import es.codeurjc.books.models.Comment;
import es.codeurjc.books.models.User;
import es.codeurjc.books.services.CommentService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private Mapper mapper;
    private CommentDatasource commentDatasource;
    private BookDatasource bookDatasource;
    private UserDatasource userDatasource;

    public CommentServiceImpl(Mapper mapper, CommentDatasource commentDatasource, BookDatasource bookDatasource,
                              UserDatasource userDatasource) {
        this.mapper = mapper;
        this.commentDatasource = commentDatasource;
        this.bookDatasource = bookDatasource;
        this.userDatasource = userDatasource;
    }

    public CommentResponseDto addComment(long bookId, CommentRequestDto commentRequestDto) {
        Book book = this.bookDatasource.findById(bookId).orElseThrow(BookNotFoundException::new);
        User user = this.userDatasource.findByNick(commentRequestDto.getUserNick()).orElseThrow(UserNotFoundException::new);
        Comment comment = this.mapper.map(commentRequestDto, Comment.class);
        comment.setBook(book);
        comment.setUser(user);
        comment = this.commentDatasource.save(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public CommentResponseDto deleteComment(long bookId, long commentId) {
        Comment comment = this.commentDatasource.findByBookIdAndId(bookId, commentId)
                .orElseThrow(CommentNotFoundException::new);
        this.commentDatasource.delete(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public Collection<UserCommentResponseDto> getComments(long userId) {
        return this.commentDatasource.findByUserId(userId).stream()
                .map(comment -> this.mapper.map(comment, UserCommentResponseDto.class))
                .collect(Collectors.toList());
    }

}
