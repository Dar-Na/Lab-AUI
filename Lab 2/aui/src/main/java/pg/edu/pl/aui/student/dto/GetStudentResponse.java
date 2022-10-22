package pg.edu.pl.aui.student.dto;

import lombok.*;
import pg.edu.pl.aui.student.entity.Student;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetStudentResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Subject {
        private String subjectName;
        private Long ectsNumber;
    }


    private Long id;
    private String name;
    private Long age;

    @Singular
    private Map<Integer, Subject> subjects;


//    public static Function<Student, GetStudentResponse> entityToDtoMapper() {
//        return student -> GetStudentResponse.builder()
//                .id(student.getId())
//                .name(student.getName())
//                .subjects(student.getSubjectList())
//                .build();
//    }

}
