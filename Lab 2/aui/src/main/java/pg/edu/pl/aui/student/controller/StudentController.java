package pg.edu.pl.aui.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.edu.pl.aui.student.dto.*;
import pg.edu.pl.aui.student.entity.Student;
import pg.edu.pl.aui.student.entity.Subject;
import pg.edu.pl.aui.student.service.StudentService;
import pg.edu.pl.aui.student.service.SubjectService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private StudentService studentService;
    private SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, SubjectService subjectService) {
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<GetStudentsResponse> getStudents() {
        List<Student> all = studentService.findAll();
        Function<Collection<Student>, GetStudentsResponse> mapper = GetStudentsResponse.entityToDtoMapper();
        GetStudentsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{student}/subjects/{id}")
    public ResponseEntity<GetSubjectResponse> getSubject(@PathVariable("student") String studentName,
                                                         @PathVariable("id") long id) {
        return subjectService.find(studentName, id)
                .map(value -> ResponseEntity.ok(GetSubjectResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{student}/subjects/")
    public ResponseEntity<GetSubjectsResponse> getSubjects(@PathVariable("student") String studentName) {
        List<Subject> all = subjectService.findAll(studentService.find(studentName).get());
        Function<Collection<Subject>, GetSubjectsResponse> mapper = GetSubjectsResponse.entityToDtoMapper();
        GetSubjectsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> postStudent(@RequestBody PostStudentRequest request, UriComponentsBuilder builder) {
        Student student = PostStudentRequest.dtoToEntityMapper().apply(request);
        student = studentService.create(student);
        return ResponseEntity.created(builder.pathSegment("api", "students", "{id}")
                .buildAndExpand(student.getId()).toUri()).build();

    }

    @PostMapping("{student}/subjects/")
    public ResponseEntity<Void> postCharacter(@PathVariable("student") String student,
                                              @RequestBody PostSubjectRequest request,
                                              UriComponentsBuilder builder) {
        Optional<Student> st = studentService.find(student);
        if (st.isPresent()) {
            Subject subject = PostSubjectRequest
                    .dtoToEntityMapper(st::get).apply(request);
            subject = subjectService.create(subject);
            return ResponseEntity.created(builder
                    .pathSegment("api", "students", "{student}", "subjects", "{id}")
                    .buildAndExpand(st.get().getName(), subject.getId()).toUri())
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{student}/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("student") String student,
                                                @PathVariable("id") long id) {
        Optional<Subject> subject = subjectService.find(student, id);
        if (subject.isPresent()) {
            subjectService.delete(subject.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{student}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("student") String student) {
        Optional<Student> st = studentService.find(student);
        if (st.isPresent()) {
            studentService.delete(st.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{student}/subjects/{id}")
    public ResponseEntity<Void> putSubject(@PathVariable("student") String student,
                                             @RequestBody PutSubjectRequest request,
                                             @PathVariable("id") long id) {
        Optional<Subject> subject = subjectService.find(student, id);
        if (subject.isPresent()) {
            PutSubjectRequest.dtoToEntityUpdater().apply(subject.get(), request);
            subjectService.update(subject.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{student}")
    public ResponseEntity<Void> putStudent(@PathVariable("student") String student,
                                           @RequestBody PutStudentRequest request) {
        Optional<Student> st = studentService.find(student);
        if (st.isPresent()) {
            PutStudentRequest.dtoToEntityUpdater().apply(st.get(), request);
            studentService.update(st.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
