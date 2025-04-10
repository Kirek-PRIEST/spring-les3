package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Reader {

  public static long sequence = 1L;

  private final long id;
  private final String name;
  private int booksIssued = 0;

  public Reader(String name) {
    this(sequence++, name);
  }

}
