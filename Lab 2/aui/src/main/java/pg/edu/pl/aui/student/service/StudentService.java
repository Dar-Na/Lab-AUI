package pg.edu.pl.aui.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.edu.pl.aui.student.entity.Student;
import pg.edu.pl.aui.student.entity.Subject;
import pg.edu.pl.aui.student.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> find(String name) {
        return studentRepository.findByName(name);
    }

    @Transactional
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void update(Student student) {
        studentRepository.save(student);
    }
}
