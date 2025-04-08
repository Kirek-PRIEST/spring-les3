package ru.gb.springdemo.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

import java.util.List;

@RestController
@RequestMapping ("/book")
public class BookController {
    @Autowired
    BookService service;

    @GetMapping
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }

    @PostMapping
    public void addBook (@RequestBody Book book){
        service.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookByID(@PathVariable long id){
        return service.getBookByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        service.deleteBookById(id);
    }


}
