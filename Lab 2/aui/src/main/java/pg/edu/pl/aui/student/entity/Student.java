package pg.edu.pl.aui.student.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "student_name")
    private String name;

    private Long age;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Subject> subjectList;

}
