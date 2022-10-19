package pg.edu.pl.aui.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pg.edu.pl.aui.datastore.DataStore;
import pg.edu.pl.aui.entity.Student;
import pg.edu.pl.aui.entity.Subject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class SubjectRepository {
    private DataStore dataStore;

    @Autowired
    public SubjectRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void create(Subject subject) {
        if (dataStore.subjectList.isEmpty()) {
            dataStore.subjectList.add(subject);
        } else if (!dataStore.subjectList.contains(subject.getSubjectName())) {
            dataStore.subjectList.add(subject);
        } else {
            System.out.println("Subject name must be unique!");
        }
    }

    public void deleteByName(String name) {
        Subject subject = this.getByName(name);
        dataStore.subjectList.remove(subject);
    }

    public Subject getByName(String name) {
        if (dataStore.subjectList == null) {
            return null;
        }
        for (int i = 0; i < dataStore.subjectList.size(); i++) {
            if (Objects.equals(dataStore.subjectList.get(i).getSubjectName(), name)) {
                return dataStore.subjectList.get(i);
            }
        }
        return null;
    }

    public List<Subject> findAll() {
        return dataStore.subjectList;
    }
}
