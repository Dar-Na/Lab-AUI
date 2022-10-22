package pg.edu.pl.aui.student.dto;

import lombok.*;
import pg.edu.pl.aui.student.entity.Student;

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
public class GetStudentsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class StudentEntry {
        private Long id;
        private String name;
    }

    @Singular
    private List<StudentEntry> students;

    public static Function<Collection<Student>, GetStudentsResponse> entityToDtoMapper() {
        return students -> {
            GetStudentsResponseBuilder response = GetStudentsResponse.builder();
            students.stream()
                    .map(student -> StudentEntry.builder()
                            .id(student.getId())
                            .name(student.getName())
                            .build())
                    .forEach(response::student);
            return response.build();
        };
    }
}
