package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    public List<Issue> getAllIssues() {
        return List.copyOf(issues);
    }

    public Issue getIssueById(long id) {
        return issues.stream().filter(issue -> Objects.equals(issue.getId(), id)).findFirst().orElse(null);
    }

    public List<Issue> getIssuesByUser(long id) {
        return issues.stream().filter(issue -> Objects.equals(issue.getReaderId(), id)).toList();
    }

    public Issue getIssueByBookId(long id) {
        return issues.stream().filter(issue -> Objects.equals(issue.getBookId(), id)).findFirst().orElse(null);
    }

    public void update(Issue issue) {
        int index = issues.indexOf(issue);
        if (index != -1) {
            issues.set(index, issue);
        }
    }

}
