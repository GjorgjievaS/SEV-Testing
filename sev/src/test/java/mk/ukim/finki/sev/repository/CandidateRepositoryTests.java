package mk.ukim.finki.sev.repository;

import mk.ukim.finki.sev.model.Candidate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class CandidateRepositoryTests {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void testFindAll() {
        Candidate candidate1 = new Candidate("Test1", "Test1", 0l);
        Candidate candidate2 = new Candidate("Test2", "Test2", 0l);
        Candidate candidate3 = new Candidate("Test3", "Test3", 0l);

        this.candidateRepository.save(candidate1);
        this.candidateRepository.save(candidate2);
        this.candidateRepository.save(candidate3);

        List<Candidate> candidates = this.candidateRepository.findAll();

        Assert.assertEquals(candidates.size(), 3);
    }

    @Test
    public void testFindById() {
        Candidate candidate1 = new Candidate("Test1", "Test1", 0l);
        Candidate candidate2 = new Candidate("Test2", "Test2", 0l);
        Candidate candidate3 = new Candidate("Test3", "Test3", 0l);

        Long candidate1Id = this.candidateRepository.save(candidate1).getId();
        this.candidateRepository.save(candidate2).getId();
        this.candidateRepository.save(candidate3).getId();

        Candidate candidate1FromDB = this.candidateRepository.findById(candidate1Id).get();

        Assert.assertNotNull(candidate1FromDB);

        Assert.assertEquals(candidate1FromDB.getFirstName(), candidate1.getFirstName());
        Assert.assertEquals(candidate1FromDB.getLastName(), candidate1.getLastName());
        Assert.assertEquals(candidate1FromDB.getNumberOfVotes(), candidate1.getNumberOfVotes());
    }

    @Test
    public void testDelete() {
        Candidate candidate1 = new Candidate("Test1", "Test1", 0l);
        Candidate candidate2 = new Candidate("Test2", "Test2", 0l);
        Candidate candidate3 = new Candidate("Test3", "Test3", 0l);

        Long candidate1Id = this.candidateRepository.save(candidate1).getId();
        this.candidateRepository.save(candidate2).getId();
        this.candidateRepository.save(candidate3).getId();

        this.candidateRepository.deleteById(candidate1Id);

        List<Candidate> candidates = this.candidateRepository.findAll();

        Assert.assertEquals(candidates.size(), 2);

    }

    @Test
    public void testDeleteAll() {
        Candidate candidate1 = new Candidate("Test1", "Test1", 0l);
        Candidate candidate2 = new Candidate("Test2", "Test2", 0l);
        Candidate candidate3 = new Candidate("Test3", "Test3", 0l);

        this.candidateRepository.save(candidate1);
        this.candidateRepository.save(candidate2);
        this.candidateRepository.save(candidate3);

        this.candidateRepository.deleteAll();

        List<Candidate> candidates = this.candidateRepository.findAll();

        Assert.assertEquals(candidates.size(), 0);

    }

    @Test
    public void testDeleteInBatch() {
        Candidate candidate1 = new Candidate("Test1", "Test1", 0l);
        Candidate candidate2 = new Candidate("Test2", "Test2", 0l);
        Candidate candidate3 = new Candidate("Test3", "Test3", 0l);

        this.candidateRepository.save(candidate1);
        Candidate candidate2DB = this.candidateRepository.save(candidate2);
        Candidate candidate3DB = this.candidateRepository.save(candidate3);

        List<Candidate> candidatesToDelete = new ArrayList<>();

        candidatesToDelete.add(candidate2DB);
        candidatesToDelete.add(candidate3DB);

        this.candidateRepository.deleteInBatch(candidatesToDelete);

        List<Candidate> candidates = this.candidateRepository.findAll();

        Assert.assertEquals(candidates.size(), 1);

    }

}
