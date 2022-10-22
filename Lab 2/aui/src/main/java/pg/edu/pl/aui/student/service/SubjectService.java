package pg.edu.pl.aui.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.edu.pl.aui.student.entity.Student;
import pg.edu.pl.aui.student.entity.Subject;
import pg.edu.pl.aui.student.repository.StudentRepository;
import pg.edu.pl.aui.student.repository.SubjectRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Subject create(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional
    public void update(Subject subject) {
        subjectRepository.save(subject);
    }

    @Transactional
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }


    public Optional<Subject> getByName(String name) {
        return subjectRepository.findBySubjectName(name);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public List<Subject> findAll(Student student) {
        return subjectRepository.findAllByStudent(student);
    }

    public Optional<Subject> find(Long id) {
        return subjectRepository.findById(id);
    }

    public Optional<Subject> find(Student student, Long id) {
        return subjectRepository.findByIdAndStudent(id, student);
    }

    public Optional<Subject> find(String studentName, Long id) {
        Optional<Student> student = studentRepository.findByName(studentName);
        if (student.isPresent()) {
            return subjectRepository.findByIdAndStudent(id, student.get());
        } else {
            return Optional.empty();
        }
    }



}
