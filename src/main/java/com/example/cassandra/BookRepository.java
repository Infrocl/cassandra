package com.example.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CassandraRepository<Book, String>{
    List<Book> findByAuthor(String author);

    @Query("SELECT * FROM book WHERE author = :author and title = :title")
    List<Book> findByTitle(@Param("author") String author, @Param("title") String title);
}
