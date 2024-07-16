package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraChallengeJavaApplication {

	private static BookService bookService;

	public LiterAluraChallengeJavaApplication(BookService bookService) {
		LiterAluraChallengeJavaApplication.bookService = bookService;
	}
	public static void main(String[] args) {

		SpringApplication.run(LiterAluraChallengeJavaApplication.class, args);
		start();
	}

	public static void start() {
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
					List<Book> books = bookService.fetchBooks(query);
					bookService.saveBooks(books);
					System.out.println("Libros guardados en la base de datos.");
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
