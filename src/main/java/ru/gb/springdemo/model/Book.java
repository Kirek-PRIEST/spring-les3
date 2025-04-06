package ru.gb.springdemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {

  public static long sequence = 1L;

  private final long id;
  private final String name;


  @JsonCreator
  public Book(@JsonProperty("name")String name) {
    this(sequence++, name);
  }

}
