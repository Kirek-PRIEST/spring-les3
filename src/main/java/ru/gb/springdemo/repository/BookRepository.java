package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("война и мир"),
                new Book("метрвые души"),
                new Book("чистый код")
        ));
    }

    public List<Book> getAllBooks(){
        return List.copyOf(books);
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public Book getBookByID(long id){
        return books.stream().filter(book -> Objects.equals(book.getId(), id))
                .findFirst().
                orElse(null);
    }
    public void deleteByID(long id){
        books.remove(books.stream().filter(book -> Objects.equals(book.getId(), id)).findFirst().orElse(null));
    }

}
