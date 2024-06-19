package com.example.micro.library.bookCatalog.mapper;

import com.example.micro.library.bookCatalog.dto.BookDto;
import com.example.micro.library.bookCatalog.models.Book;

public class BookMapper {

    public static Book mapToBook(BookDto bookDto, Book book){
        book.setAuthor(bookDto.getAuthor());
        book.setAvailable(bookDto.isAvailable());
        book.setGenre(bookDto.getGenre());
        book.setTitle(bookDto.getTitle());
        return book;
    }
    public static BookDto mapToBookDto(Book book, BookDto  bookDto){
        bookDto.setAuthor(book.getAuthor());
        bookDto.setAvailable(book.isAvailable());
        bookDto.setGenre(book.getGenre());
        bookDto.setTitle(book.getTitle());
        return bookDto;
    }
}
