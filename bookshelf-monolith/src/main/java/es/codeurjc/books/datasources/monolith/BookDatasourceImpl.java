package es.codeurjc.books.datasources.monolith;

import es.codeurjc.books.datasources.BookDatasource;
import es.codeurjc.books.models.Book;
import es.codeurjc.books.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Profile("monolith")
public class BookDatasourceImpl implements BookDatasource {

    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return bookRepository.findById(bookId);
    }
}
