package pg.edu.pl.aui.entity;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private String name;
    private int index;
    private List<Subject> subjectList;

    public Student(String name, int index, List<Subject> subjectList) {
        this.index = index;
        this.name = name;
        this.subjectList = subjectList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "Student[name=" + name + ", index=" + index + ", subjects=" + subjectList + "]";
    }
}
