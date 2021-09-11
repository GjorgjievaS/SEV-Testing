package mk.ukim.finki.sev.service;

import mk.ukim.finki.sev.model.Candidate;
import mk.ukim.finki.sev.repository.CandidateRepository;
import mk.ukim.finki.sev.service.impl.CandidateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceTests {
    
    @Mock
    CandidateRepository candidateRepository;

    @Mock
    CandidateService candidateService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Candidate candidate1 = new Candidate("Test1", "Test1", 0l);
        Candidate candidate2 = new Candidate("Test2", "Test2", 0l);
        Candidate candidate3 = new Candidate("Test3", "Test3", 0l);

        List<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate1);
        candidates.add(candidate2);
        candidates.add(candidate3);

        Mockito.when(this.candidateRepository.save(Mockito.any(Candidate.class)))
                .thenReturn(candidate1);

        Mockito.when(this.candidateRepository.findAll())
                .thenReturn(candidates);

        Mockito.when(this.candidateRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(candidate1));

        Mockito.when(this.candidateService.deleteById(Mockito.anyLong()))
                .thenReturn(candidate1);

        this.candidateService = Mockito.spy(new CandidateServiceImpl(this.candidateRepository));
    }

    @Test
    public void testCreate() {
        Candidate candidateFromDB = this.candidateService.create("Test1", "Test1", 0l);

        Assertions.assertEquals("Test1", candidateFromDB.getFirstName());
        Assertions.assertEquals("Test1", candidateFromDB.getLastName());
        Assertions.assertEquals(0l, candidateFromDB.getNumberOfVotes());
    }

    @Test
    public void testFindAll() {
        List<Candidate> candidates = this.candidateService.findAll();
        Assertions.assertEquals(3, candidates.size());
    }

    @Test
    public void testFindById() {
        Candidate candidateFromDB = this.candidateService.findById(5l);

        Assertions.assertEquals("Test1", candidateFromDB.getFirstName());
        Assertions.assertEquals("Test1", candidateFromDB.getLastName());
        Assertions.assertEquals(0l, candidateFromDB.getNumberOfVotes());
    }

    @Test
    public void testUpdate() {
        Candidate candidate = new Candidate("Test1", "Test1", 0l);
        Candidate candidateFromDB = this.candidateService.update(candidate);

        Assertions.assertEquals("Test1", candidateFromDB.getFirstName());
        Assertions.assertEquals("Test1", candidateFromDB.getLastName());
        Assertions.assertEquals(0l, candidateFromDB.getNumberOfVotes());
    }

    @Test
    public void deleteById() {
        Candidate deletedCandidate = this.candidateService.deleteById(5l);

        Assertions.assertEquals("Test1", deletedCandidate.getFirstName());
        Assertions.assertEquals("Test1", deletedCandidate.getLastName());
        Assertions.assertEquals(0l, deletedCandidate.getNumberOfVotes());
    }


}
