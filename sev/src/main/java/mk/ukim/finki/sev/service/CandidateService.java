package mk.ukim.finki.sev.service;

import mk.ukim.finki.sev.model.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate create(String firstName, String lastName, Long numberOfVotes);
    List<Candidate> findAll();
    Candidate findById(Long id);
    Candidate update(Candidate candidate);
    Candidate deleteById(Long id);
}
