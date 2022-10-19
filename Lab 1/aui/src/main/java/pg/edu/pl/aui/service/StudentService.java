package pg.edu.pl.aui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.edu.pl.aui.entity.Student;
import pg.edu.pl.aui.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void create(Student student) {
        studentRepository.create(student);
    }

    public void deleteById(int index) {
        studentRepository.deleteById(index);
    }

    public Student getById(int index) {
        return studentRepository.getById(index);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
