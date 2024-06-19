package com.example.micro.library.bookCatalog.controller;

import com.example.micro.library.bookCatalog.constants.BookConstants;
import com.example.micro.library.bookCatalog.dto.BookContactInfoDto;
import com.example.micro.library.bookCatalog.dto.BookDto;
import com.example.micro.library.bookCatalog.dto.ResponseDto;
import com.example.micro.library.bookCatalog.exception.BooksNotFoundException;
import com.example.micro.library.bookCatalog.models.Book;
import com.example.micro.library.bookCatalog.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class BooksController {
    private final IBookService iBookService;
    @Autowired
    private BookContactInfoDto bookContactInfoDto;

    public BooksController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = iBookService.getAllBooks();
        if (books.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(books);
        }
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> addBook(@RequestBody BookDto bookDto){
        iBookService.addBook(bookDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.MESSAGE_201,BookConstants.STATUS_201));
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateBook(@RequestBody BookDto bookDto) {
        try {
            BookDto updatedBook = iBookService.updateBook(bookDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(BookConstants.STATUS_200, BookConstants.MESSAGE_200));
        } catch (BooksNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(BookConstants.STATUS_404, BookConstants.MESSAGE_404));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(BookConstants.STATUS_500, BookConstants.MESSAGE_500));
        }
    }



    @DeleteMapping("/deleteByTitleAndAuthor")
    public ResponseEntity<ResponseDto> deleteByTitleAndAuthor(@RequestParam String title,@RequestParam String author) {
        boolean isDeleted = iBookService.deleteBookByAuthorAndTitle(title, author);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(BookConstants.STATUS_200,BookConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(BookConstants.STATUS_417,BookConstants.MESSAGE_417_DELETE));
        }
    }



    @DeleteMapping("/deleteByAuthor")
    public ResponseEntity<ResponseDto> deleteByAuthor(@RequestParam  String author) {
        boolean isDeleted = iBookService.deleteBookByAuthor(author);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(BookConstants.STATUS_200,BookConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(BookConstants.STATUS_417,BookConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/contact-info")
    public ResponseEntity<BookContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookContactInfoDto);
    }
}

