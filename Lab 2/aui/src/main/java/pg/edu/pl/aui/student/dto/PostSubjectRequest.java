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
public class PostSubjectRequest {
    private String subjectName;
    private Long ectsNumber;

    public static Function<PostSubjectRequest, Subject> dtoToEntityMapper(
            Supplier<Student> studentSupplier) {
        return request -> Subject.builder()
                .subjectName(request.getSubjectName())
                .ectsNumber(request.getEctsNumber())
                .student(studentSupplier.get())
                .build();
    }

}
