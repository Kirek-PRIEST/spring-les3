package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Issue> getIssues(long id){
        return issueRepository.getIssuesByUser(id);
    }
    public void deleteReader(@RequestParam long id){
        readerRepository.deleteReader(id);
    }
    public Reader getReaderById (@RequestParam long id){
        return readerRepository.getReaderById(id);
    }
    public void addReader(@RequestBody Reader reader){
        readerRepository.addReader(reader);
    }
    public List<Reader> getAllReaders(){
        return readerRepository.getAllReaders();
    }
}
