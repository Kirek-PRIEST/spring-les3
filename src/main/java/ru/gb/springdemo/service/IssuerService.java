package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.exeptions.MaxBooksLimitExeption;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IssuerService {

    // спринг это все заинжектит
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    @Value("${application.max-allowed-books:1}")
    private int myProperty;

    public Issue issue(IssueRequest request) {
        check(request);
        if (readerRepository.getReaderById(request.getReaderId()).getBooksIssued() >=myProperty){
            throw new MaxBooksLimitExeption("Превышен лимит выдачи книг для пользователя с идентификатором \"" + request.getReaderId() + "\"");
        };


        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        readerRepository.getReaderById(request.getReaderId()).setBooksIssued(readerRepository.getReaderById(request.getReaderId()).getBooksIssued() + 1);
        issueRepository.save(issue);
        return issue;
    }

    public Issue returnBook(long id){
        Issue issue = issueRepository.getIssueById(id);
        if (issue == null) {
            throw new NoSuchElementException("Выдача книги со следующим идентификатором не найдена: " + id);
        }
        issue.setReturned_at(LocalDateTime.now());
        readerRepository.getReaderById(id).setBooksIssued(readerRepository.getReaderById(id).getBooksIssued() - 1);
        issueRepository.update(issue);
        return issue;
    }

    public Issue returnBook(IssueRequest request) {
        check(request);
        List<Issue> list = issueRepository.getIssuesByUser(request.getReaderId());
        Issue issue = Objects.requireNonNull(list.stream().filter(is -> Objects.equals(is.getBookId(), request.getBookId())).findFirst().orElse(null));
        issue.setReturned_at(LocalDateTime.now());
        readerRepository.getReaderById(request.getReaderId()).setBooksIssued(readerRepository.getReaderById(request.getReaderId()).getBooksIssued() - 1);
        return issue;
    }

    public List<Issue> getAllIssues(){
        return issueRepository.getAllIssues();
    }

    public List<Issue> getIssuesByReaderId(long id){
        return issueRepository.getIssuesByUser(id);
    }
    public Issue getIssueById(long id){
        return issueRepository.getIssueById(id);
    }

    private boolean check(IssueRequest request) {
        if (bookRepository.getBookByID(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        return true;
    }

}