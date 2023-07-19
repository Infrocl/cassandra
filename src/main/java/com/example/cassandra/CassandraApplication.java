package com.example.cassandra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(BookRepository bookRepository) {
		return args -> {
			Book firstBook = new Book("Л.Н. Толстой", "Война и мир", 2018);
			Book secondBook = new Book("Л.Н. Толстой","Воскресение", 2020);
			Book thirdBook = new Book("Г.Дж. Уэллс", "Остров доктора Моро",2009);
			Book forthBook = new Book("Г.Дж. Уэллс", "Война миров", 2023);
			bookRepository.saveAll(Arrays.asList(firstBook, secondBook, thirdBook, forthBook));
			System.out.println("Все книги: ");
			System.out.println(bookRepository.findAll());
			System.out.println("Поиск по автору: ");
			System.out.println(bookRepository.findByAuthor("Г.Дж. Уэллс"));
			System.out.println("Поиск по названию: ");
			System.out.println(bookRepository.findByTitle("Г.Дж. Уэллс", "Война миров"));
		};
	}
}
