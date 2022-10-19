package pg.edu.pl.aui.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pg.edu.pl.aui.datastore.DataStore;
import pg.edu.pl.aui.entity.Student;

import java.util.List;
import java.util.Objects;

@Repository
public class StudentRepository {
    private DataStore dataStore;

    @Autowired
    public StudentRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void create(Student student) {
        if (dataStore.studentList.isEmpty()) {
            dataStore.studentList.add(student);
        } else if (!dataStore.studentList.contains(student.getIndex())) {
            dataStore.studentList.add(student);
        } else {
            System.out.println("Student index must be unique!");
        }
    }

    public void deleteById(int index) {
        Student student = this.getById(index);
        dataStore.studentList.remove(student);
    }

    public Student getById(int index) {
        if (dataStore.studentList == null) {
            return null;
        }
        for (int i = 0; i < dataStore.studentList.size(); i++) {
            if (Objects.equals(dataStore.studentList.get(i).getIndex(), index)) {
                return dataStore.studentList.get(i);
            }
        }
        return null;
    }

    public List<Student> findAll() {
        return dataStore.studentList;
    }

}
