package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraChallengeJavaApplication implements CommandLineRunner {

	private final BookService bookService;

	@Autowired
	public LiterAluraChallengeJavaApplication(BookService bookService) {
		this.bookService = bookService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		start();
	}

	private void start() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1. Buscar libros");
			System.out.println("2. Ver todos los libros");
			System.out.println("3. Buscar por autor");
			System.out.println("4. Buscar por ID");
			System.out.println("5. Salir");
			int choice = scanner.nextInt();
			scanner.nextLine();  // consume newline
			switch (choice) {
				case 1:
					System.out.println("Ingrese su consulta (ej. search=dickens):");
					String query = scanner.nextLine();
					try {
						List<Book> books = bookService.fetchBooks(query);
						bookService.saveBooks(books);
						System.out.println("Libros guardados en la base de datos.");
					} catch (IOException | InterruptedException e) {
						System.out.println("Error al buscar libros: " + e.getMessage());
					}
					break;
				case 2:
					List<Book> allBooks = bookService.getAllBooks();
					allBooks.forEach(System.out::println);
					break;
				case 3:
					System.out.println("Ingrese el nombre del autor:");
					String author = scanner.nextLine();
					List<Book> booksByAuthor = bookService.findBooksByAuthor(author);
					booksByAuthor.forEach(System.out::println);
					break;
				case 4:
					System.out.println("Ingrese el ID del libro:");
					Long id = scanner.nextLong();
					Book book = bookService.findBookById(id);
					System.out.println(book);
					break;
				case 5:
					System.exit(0);
			}
		}
	}
}
