package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService service;

    @GetMapping
    public List<Reader> getAllReaders(){
        return service.getAllReaders();
    }
    @PostMapping
    public void addReader(@RequestBody Reader reader){
        service.addReader(reader);
    }

    @GetMapping("/{id}")
    public Reader reader(@PathVariable long id){
        return service.getReaderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable long id){
        service.deleteReader(id);
    }
    @GetMapping("/{id}/issue")
    public List<Issue> getIssuedBooks(@PathVariable long id){
        return service.getIssues(id);
    }
}
