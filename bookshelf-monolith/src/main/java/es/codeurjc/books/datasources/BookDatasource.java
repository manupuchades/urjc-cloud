package es.codeurjc.books.datasources;

import es.codeurjc.books.dtos.requests.BookRequestDto;
import es.codeurjc.books.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.books.dtos.responses.BookResponseDto;
import es.codeurjc.books.models.Book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookDatasource {

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(long bookId);

}
