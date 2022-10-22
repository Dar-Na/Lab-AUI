package pg.edu.pl.aui.student.dto;

import lombok.*;
import pg.edu.pl.aui.student.entity.Subject;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSubjectResponse {
    private Long id;
    private String name;
    private Long ectsNumber;

    public static Function<Subject, GetSubjectResponse> entityToDtoMapper() {
        return subject -> GetSubjectResponse.builder()
                .id(subject.getId())
                .name(subject.getSubjectName())
                .ectsNumber(subject.getEctsNumber())
                .build();
    }
}
