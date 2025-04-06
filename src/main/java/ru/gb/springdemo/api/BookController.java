package ru.gb.springdemo.api;


import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping ("/book")
public class BookController {
    //@Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }


    @PostMapping
    public void addBook (@RequestBody Book book){
        bookRepository.addBook(book);
    }
    @GetMapping("/{id}")
    public Book getBookByID(@PathVariable long id){
        return bookRepository.getBookByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookRepository.deleteByID(id);
    }


}
