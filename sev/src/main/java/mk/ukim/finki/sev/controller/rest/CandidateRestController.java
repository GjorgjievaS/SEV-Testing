package mk.ukim.finki.sev.controller.rest;

import mk.ukim.finki.sev.model.Candidate;
import mk.ukim.finki.sev.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateRestController {

    private final CandidateService candidateService;

    public CandidateRestController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> findAll() {
        return ResponseEntity.ok(this.candidateService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Candidate> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.candidateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Candidate> create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Long numberOfVotes) {
        return ResponseEntity.ok(this.candidateService.create(firstName, lastName, numberOfVotes));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Candidate> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.candidateService.deleteById(id));
    }
}
