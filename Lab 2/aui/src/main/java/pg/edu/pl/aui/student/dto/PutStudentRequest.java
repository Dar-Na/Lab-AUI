package pg.edu.pl.aui.student.dto;

import lombok.*;
import pg.edu.pl.aui.student.entity.Student;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutStudentRequest {
    private String name;
    private Long age;

    public static BiFunction<Student, PutStudentRequest, Student> dtoToEntityUpdater() {
        return (student, request) -> {
            student.setName(request.getName());
            student.setAge(request.getAge());
            return student;
        };
    }
}
