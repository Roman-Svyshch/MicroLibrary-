package com.example.micro.library.bookCatalog.service.serviceImpl;

import com.example.micro.library.bookCatalog.dto.BookDto;
import com.example.micro.library.bookCatalog.exception.BookAlreadyExistsException;
import com.example.micro.library.bookCatalog.exception.BooksNotFoundException;
import com.example.micro.library.bookCatalog.mapper.BookMapper;
import com.example.micro.library.bookCatalog.models.Book;
import com.example.micro.library.bookCatalog.repository.BooksRepository;
import com.example.micro.library.bookCatalog.service.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IBookServiceImpl implements IBookService {

    @Autowired
    public BooksRepository booksRepository;
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = booksRepository.findAll();
        if (books.isEmpty()){
            throw new BooksNotFoundException("There are no books available.");
        }
        return books;
    }

    @Override
    public Book getBookById(long id) {
        Book book = booksRepository
                .findById(id)
                .orElseThrow(()-> new BooksNotFoundException("Book with id "  + id + " not found"));

        return book;
    }

    @Override
    public Book addBook(BookDto bookDto) {

        Book newBook = BookMapper.mapToBook(bookDto,new Book());
        Optional<Book> optionalBook =  booksRepository.findByTitle(bookDto.getTitle());
        if (optionalBook.isPresent()){
            throw new BookAlreadyExistsException("Book already registered with given title " + bookDto.getTitle());
        }
        return  booksRepository.save(newBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        if (bookDto == null) {
            throw new IllegalArgumentException("BookDto cannot be null");
        }

        // Check if the book exists
        Optional<Book> existingBookOptional = booksRepository.findById(bookDto.getId());
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            // Update the existing book with the new data
            existingBook.setTitle(bookDto.getTitle());
            existingBook.setAuthor(bookDto.getAuthor());
            existingBook.setGenre(bookDto.getGenre());
            existingBook.setAvailable(bookDto.isAvailable());

            // Save the updated book
            booksRepository.save(existingBook);

            return BookMapper.mapToBookDto(existingBook, new BookDto());
        } else {
            throw new BooksNotFoundException("Book not found with id " + bookDto.getId());
        }
    }

    @Transactional
    @Override
    public boolean deleteBookByAuthorAndTitle(String title, String author) {

    Book book = booksRepository.findByAuthorAndTitle(author,title).orElseThrow(
            ()-> new BooksNotFoundException("Book with title :" + title + " and author :" + author + " not found")
    );
    booksRepository.deleteByAuthorAndTitle(author,title);
    return true;
    }

    @Transactional
    @Override
    public boolean deleteBookByAuthor(String author) {
        List<Book> books = booksRepository.findByAuthor(author);
            if (!books.isEmpty()){
                booksRepository.deleteAll(books);
                return true;
            }else return false;
    }


}
