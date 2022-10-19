package pg.edu.pl.aui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.edu.pl.aui.entity.Student;
import pg.edu.pl.aui.entity.Subject;
import pg.edu.pl.aui.service.StudentService;
import pg.edu.pl.aui.service.SubjectService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class InitData {
    private StudentService studentService;
    private SubjectService subjectService;

    @Autowired
    public InitData(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @PostConstruct
    private synchronized void init() {
        Subject subject1 = new Subject("test name", 13);
        Subject subject2 = new Subject("matma", 5000);
        Subject subject3 = new Subject("poo", 3);

        subjectService.create(subject1);
        subjectService.create(subject2);
        subjectService.create(subject3);

        Student student1 = new Student("student1", 1, subjectService.findAll());
        Student student2 = new Student("student2", 3, subjectService.findAll());
        Student student3 = new Student("student3", 12, new ArrayList<Subject>(List.of(subjectService.getByName("poo"))));

        studentService.create(student1);
        studentService.create(student2);
        studentService.create(student3);
    }
}
