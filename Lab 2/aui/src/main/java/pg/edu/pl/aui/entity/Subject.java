package pg.edu.pl.aui.entity;

import java.io.Serializable;

public class Subject implements Serializable {
    private String subjectName;
    private int ectsNumber;

    public Subject(String subjectName, int ectsNumber) {
        this.ectsNumber = ectsNumber;
        this.subjectName = subjectName;
    }

    public int getEctsNumber() {
        return ectsNumber;
    }

    public void setEctsNumber(int ectsNumber) {
        this.ectsNumber = ectsNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject[name=" + subjectName + ", ectsNumber=" + ectsNumber + "]";
    }
}
