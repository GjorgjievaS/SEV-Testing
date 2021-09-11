package mk.ukim.finki.sev.service.impl;

import mk.ukim.finki.sev.model.Candidate;
import mk.ukim.finki.sev.repository.CandidateRepository;
import mk.ukim.finki.sev.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate create(String firstName, String lastName, Long numberOfVotes) {
        Candidate candidate = new Candidate(firstName, lastName, numberOfVotes);
        return this.candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> findAll() {
        return this.candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return this.candidateRepository.findById(id).get();
    }

    @Override
    public Candidate update(Candidate candidate) {
        return this.candidateRepository.save(candidate);
    }

    @Override
    public Candidate deleteById(Long id) {
        Candidate candidate = this.candidateRepository.findById(id).get();
        this.candidateRepository.deleteById(id);
        return candidate;
    }
}
