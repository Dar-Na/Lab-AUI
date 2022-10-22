package pg.edu.pl.aui.student.dto;

import lombok.*;
import pg.edu.pl.aui.student.entity.Subject;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSubjectsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class SubjectEntry {
        private Long id;
        private String name;
    }

    @Singular
    private List<SubjectEntry> subjects;

    public static Function<Collection<Subject>, GetSubjectsResponse> entityToDtoMapper() {
        return subjects -> {
            GetSubjectsResponseBuilder response = GetSubjectsResponse.builder();
            subjects.stream()
                    .map(subject -> SubjectEntry.builder()
                            .id(subject.getId())
                            .name(subject.getSubjectName())
                            .build())
                    .forEach(response::subject);
            return response.build();
        };
    }

}
