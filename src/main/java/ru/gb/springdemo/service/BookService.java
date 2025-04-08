package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }
    public Book getBookByID(long id) {
        return bookRepository.getBookByID(id);
    }

    public void addBook(Book book){
        bookRepository.addBook(book);
    }

    public void deleteBookById(long id){
        bookRepository.deleteByID(id);
    }
}