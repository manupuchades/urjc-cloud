package es.codeurjc.books.services.impl;

import es.codeurjc.books.datasources.BookDatasource;
import es.codeurjc.books.dtos.requests.BookRequestDto;
import es.codeurjc.books.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.books.dtos.responses.BookResponseDto;
import es.codeurjc.books.exceptions.BookNotFoundException;
import es.codeurjc.books.models.Book;
import es.codeurjc.books.services.BookService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private Mapper mapper;
    private BookDatasource bookDatasource;

    public BookServiceImpl(Mapper mapper, BookDatasource bookDatasource) {
        this.mapper = mapper;
        this.bookDatasource = bookDatasource;
    }

    public Collection<BookResponseDto> findAll() {
        return this.bookDatasource.findAll().stream()
                .map(book -> this.mapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    public BookDetailsResponseDto save(BookRequestDto bookRequestDto) {
        Book book = this.mapper.map(bookRequestDto, Book.class);
        book = this.bookDatasource.save(book);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

    public BookDetailsResponseDto findById(long bookId) {
        Book book = this.bookDatasource.findById(bookId).orElseThrow(BookNotFoundException::new);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

}
