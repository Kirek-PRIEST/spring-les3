package ru.gb.springdemo.exeptions;

public class MaxBooksLimitExeption extends RuntimeException {
    public MaxBooksLimitExeption(String message) {
        super(message);
    }
}
