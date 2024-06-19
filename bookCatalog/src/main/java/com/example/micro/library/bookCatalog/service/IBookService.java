package com.example.micro.library.bookCatalog.service;

import com.example.micro.library.bookCatalog.dto.BookDto;
import com.example.micro.library.bookCatalog.models.Book;

import java.util.List;

public interface IBookService {

     List<Book> getAllBooks();
     Book getBookById(long id);
     Book addBook(BookDto bookDto);
     BookDto updateBook(BookDto bookDto);
     boolean deleteBookByAuthorAndTitle(String title,String author);
     boolean deleteBookByAuthor(String author);
}
