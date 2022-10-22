package pg.edu.pl.aui.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.edu.pl.aui.student.dto.GetSubjectResponse;
import pg.edu.pl.aui.student.dto.GetSubjectsResponse;
import pg.edu.pl.aui.student.dto.PostSubjectRequest;
import pg.edu.pl.aui.student.dto.PutSubjectRequest;
import pg.edu.pl.aui.student.entity.Subject;
import pg.edu.pl.aui.student.service.SubjectService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/subjects")
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<GetSubjectsResponse> getSubjects() {
        List<Subject> all = subjectService.findAll();
        Function<Collection<Subject>, GetSubjectsResponse> mapper = GetSubjectsResponse.entityToDtoMapper();
        GetSubjectsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSubjectResponse> getSubject(@PathVariable("id") long id) {
        return subjectService.find(id)
                .map(v -> ResponseEntity.ok(GetSubjectResponse.entityToDtoMapper().apply(v)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postSubject(@RequestBody PostSubjectRequest request, UriComponentsBuilder builder) {
        Subject subject = PostSubjectRequest.dtoToEntityMapper(() -> null ).apply(request);
        subject = subjectService.create(subject);
        return ResponseEntity.created(builder.pathSegment("api", "subjects", "{id}")
                .buildAndExpand(subject.getId()).toUri()).build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("id") long id) {
        Optional<Subject> subject = subjectService.find(id);
        if (subject.isPresent()) {
            subjectService.delete(subject.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putSubject(@RequestBody PutSubjectRequest request, @PathVariable("id") long id) {
        Optional<Subject> subject = subjectService.find(id);
        if (subject.isPresent()) {
            PutSubjectRequest.dtoToEntityUpdater().apply(subject.get(), request);
            subjectService.update(subject.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
