package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BookResponse;
import com.example.demo.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {
    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;
    private final String BASE_URL = "https://gutendex.com/books";

    public BookService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchBooks(String query) {
        String url = BASE_URL + "?" + query;
        ResponseEntity<BookResponse> response = restTemplate.getForEntity(url, BookResponse.class);
        return response.getBody().getResults();
    }

    public void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return bookRepository.findByAuthors_NameContainingIgnoreCase(authorName);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
