package library.management.system.librarymanagement.service;

import library.management.system.librarymanagement.exception.ResourceAlreadyExistException;
import library.management.system.librarymanagement.exception.ResourceNotFoundException;
import library.management.system.librarymanagement.model.Issues;
import library.management.system.librarymanagement.repository.IssuesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IssuesService {

    private IssuesRepository issuesRepository;

    public IssuesService(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    public Issues issueBook(Issues issues){
        Optional<Issues> optionalIssues = issuesRepository.findById(issues.getId());
        if (optionalIssues.isPresent()){
            throw new ResourceAlreadyExistException("OOPS Issue Id "+issues.getId()+" is already exist");
        }
        else {
            return issuesRepository.save(issues);
        }
    }

    public Iterable<Issues> getAllIssues(){
        return issuesRepository.findAll();
    }

    public Issues getIssues(int issueId){
        Optional<Issues> optionalIssues = issuesRepository.findById(issueId);
        if (optionalIssues.isPresent()){
            return optionalIssues.get();
        }
        else {
            throw new ResourceNotFoundException("Issue Id "+issueId+" does not exist in table");
        }
    }

    public Issues editIssues(Issues issues, int issueId){
        Optional<Issues> optionalIssues = issuesRepository.findById(issueId);
        if (optionalIssues.isPresent()){
            issues.setId(issueId);
            return issuesRepository.save(issues);
        }
        throw new ResourceNotFoundException("Issue id "+issueId+" does not exist in table");
    }

    public void clearIssue(int issueId){
        Optional<Issues> optionalIssues = issuesRepository.findById(issueId);
        if (optionalIssues.isPresent()){
            issuesRepository.deleteById(issueId);
        }
        else {
            throw new ResourceNotFoundException("Issue id "+issueId+" does not exist in table");
        }
    }
}
