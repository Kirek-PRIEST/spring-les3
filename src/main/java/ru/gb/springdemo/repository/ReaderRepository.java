package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
      new ru.gb.springdemo.model.Reader("Игорь")
    ));
  }

  public List<Reader> getAllReaders(){
    return List.copyOf(readers);
  }

  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void addReader(Reader reader){
    readers.add(reader);
  }
  public void deleteReader(long id){
    readers.remove(readers.stream().filter(reader -> Objects.equals(reader.getId(), id)).findFirst().orElse(null));
  }

}
