package pg.edu.pl.aui.student.dto;

import lombok.*;
import pg.edu.pl.aui.student.entity.Subject;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutSubjectRequest {
    private String subjectName;
    private Long ectsNumber;

    public static BiFunction<Subject, PutSubjectRequest, Subject> dtoToEntityUpdater() {
        return (subject, request) -> {
            subject.setSubjectName(request.getSubjectName());
            subject.setEctsNumber(request.getEctsNumber());
            return subject;
        };
    }

}
