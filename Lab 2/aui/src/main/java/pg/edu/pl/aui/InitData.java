package pg.edu.pl.aui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.edu.pl.aui.student.entity.Student;
import pg.edu.pl.aui.student.entity.Subject;
import pg.edu.pl.aui.student.service.StudentService;
import pg.edu.pl.aui.student.service.SubjectService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class InitData {
    private StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public InitData(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @PostConstruct
    private synchronized void init() {
        Student kevin = Student.builder()
                .name("kevin")
                .age(1l)
                .build();

        Student martin = Student.builder()
                .name("martin")
                .age(25l)
                .build();


        Student meg = Student.builder()
                .name("meg")
                .age(15l)
                .build();

        studentService.create(kevin);
        studentService.create(martin);
        studentService.create(meg);

        Subject matma = Subject.builder()
                .subjectName("matma")
                .ectsNumber(13L)
                .student(martin)
                .build();

        Subject paa = Subject.builder()
                .subjectName("paa")
                .ectsNumber(54353L)
                .student(meg)
                .build();

        Subject ako = Subject.builder()
                .subjectName("ako")
                .ectsNumber(1L)
                .student(meg)
                .build();

        subjectService.create(matma);
        subjectService.create(paa);
        subjectService.create(ako);
//        Optional<Student> m = studentService.find("meg");
//        subjectService.create(Subject.builder().subjectName("mamamm").ectsNumber(1311l).student(m.get()).build());




    }
}
