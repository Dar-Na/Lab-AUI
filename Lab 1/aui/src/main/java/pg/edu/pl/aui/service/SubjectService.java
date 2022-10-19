package pg.edu.pl.aui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.edu.pl.aui.entity.Student;
import pg.edu.pl.aui.entity.Subject;
import pg.edu.pl.aui.repository.StudentRepository;
import pg.edu.pl.aui.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void create(Subject subject) {
        subjectRepository.create(subject);
    }

    public void deleteByName(String name) {
        subjectRepository.deleteByName(name);
    }

    public Subject getByName(String name) {
        return subjectRepository.getByName(name);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
}
