package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BookResponse;
import com.example.demo.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class BookService {
    private final HttpClient httpClient;
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    private final String BASE_URL = "https://gutendex.com/books";

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.httpClient = HttpClient.newHttpClient();
        this.bookRepository = bookRepository;
        this.objectMapper = new ObjectMapper();
    }

    public List<Book> fetchBooks(String query) throws IOException, InterruptedException {
        String url = BASE_URL + "?" + query;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        BookResponse bookResponse = objectMapper.readValue(response.body(), new TypeReference<BookResponse>() {});

        return bookResponse.getResults();
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

