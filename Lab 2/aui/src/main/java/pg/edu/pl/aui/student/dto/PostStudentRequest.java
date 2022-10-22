package pg.edu.pl.aui.student.dto;

import lombok.*;
import pg.edu.pl.aui.student.entity.Student;
import pg.edu.pl.aui.student.entity.Subject;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostStudentRequest {
    private String name;
    private Long age;
    public static Function<PostStudentRequest, Student> dtoToEntityMapper() {
        return request -> Student.builder()
                .name(request.getName())
                .age(request.getAge())
                .build();
    }
}
