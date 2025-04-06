package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.exeptions.MaxBooksLimitExeption;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

  @Autowired
  private IssuerService service;



  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }catch (MaxBooksLimitExeption e){
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Issue> returnBook(@PathVariable long id) {
    try {
      Issue issue = service.returnBook(id);
      return ResponseEntity.ok(issue);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
    public List<Issue> getAllIssues(){
      return service.getAllIssues();
  }

  @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable long id){
      return service.getIssueById(id);
  }

}
