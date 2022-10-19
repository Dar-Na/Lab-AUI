package pg.edu.pl.aui.datastore;

import org.springframework.stereotype.Component;
import pg.edu.pl.aui.entity.Student;
import pg.edu.pl.aui.entity.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class DataStore {
    public ArrayList<Student> studentList = new ArrayList<Student>();

    public ArrayList<Subject> subjectList = new ArrayList<Subject>();

//    public void createStudent(Student student) {}
//    public void createSubject(Subject subject) {}
//    public void deleteStudentByIndex(int index) {}
//    public void deleteSubjectByName(String name) {}
//    public Student getStudentById(int index) {}
//    public Subject getSubjectByName(String name) {}
//    public List<Student> findAllStudents() {}
//    public List<Subject> findAllSubjects() {}
}
