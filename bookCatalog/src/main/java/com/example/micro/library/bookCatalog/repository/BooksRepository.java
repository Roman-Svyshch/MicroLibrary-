package com.example.micro.library.bookCatalog.repository;

import com.example.micro.library.bookCatalog.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BooksRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitle(String title);
    List<Book>findByAuthor(String author);
    Optional<Book> deleteByAuthorAndTitle(String author,String title);
    Optional<Book> findByAuthorAndTitle(String author,String title);
      void  deleteByAuthor(String author);
}
