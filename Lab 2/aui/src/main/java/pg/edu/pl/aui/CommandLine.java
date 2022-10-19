package pg.edu.pl.aui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pg.edu.pl.aui.entity.Student;
import pg.edu.pl.aui.entity.Subject;
import pg.edu.pl.aui.service.StudentService;
import pg.edu.pl.aui.service.SubjectService;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private StudentService studentService;
    private SubjectService subjectService;

    @Autowired
    public CommandLine(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String nextLine = "";
        String startUpInfo = """
                stop\s
                create subject\s
                delete subject\s
                find all subjects\s
                find subject\s
                create student\s
                delete student\s
                find all students\s
                find student""";
        System.out.println(startUpInfo);
        while (!Objects.equals(nextLine, "stop")) {
            nextLine = scanner.nextLine();
            if (Objects.equals(nextLine, "create subject")) {
                Subject subject = createSubject(scanner);
                subjectService.create(subject);
                System.out.println("subject created");
            }

            if (Objects.equals(nextLine, "delete subject")) {
                System.out.println("write subject name to delete");
                nextLine = scanner.nextLine();
                subjectService.deleteByName(nextLine);
                System.out.println("deleted");
            }

            if (Objects.equals(nextLine, "find all subjects")) {
                System.out.println(subjectService.findAll());
            }

            if (Objects.equals(nextLine, "find subject")) {
                System.out.println("write subject name");
                nextLine = scanner.nextLine();
                System.out.println(subjectService.getByName(nextLine));
            }

            if (Objects.equals(nextLine, "create student")) {
                Student student = createStudent(scanner);
                studentService.create(student);
                System.out.println("student created");
            }

            if (Objects.equals(nextLine, "delete student")) {
                System.out.println("write student id to delete");
                int studentId = scanner.nextInt();
                studentService.deleteById(studentId);
                System.out.println("deleted");
            }

            if (Objects.equals(nextLine, "find all students")) {
                System.out.println(studentService.findAll());
            }

            if (Objects.equals(nextLine, "find student")) {
                System.out.println("write student id");
                int studentId = scanner.nextInt();
                System.out.println(studentService.getById(studentId));
            }
        }
    }

    public Subject createSubject(Scanner scanner) {
        System.out.println("write subject name: ");
        String subjectName = scanner.nextLine();
        System.out.println("write subject ECTS number: ");
        int subjectECTS = scanner.nextInt();
        while (subjectService.getByName(subjectName) != null) {
            System.out.println("Please write unique subject name: ");
            subjectName = scanner.nextLine();
        }
        return new Subject(subjectName, subjectECTS);
    }

    public Student createStudent(Scanner scanner) {
        ArrayList<Subject> subjectArrayList = new ArrayList<Subject>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        System.out.println("write student name: ");
        String studentName = scanner.nextLine();

        System.out.println("write student id: ");
        int studentId = scanner.nextInt();

        while (studentService.getById(studentId) != null) {
            System.out.println("Please write unique student id: ");
            studentId = scanner.nextInt();
        }

        System.out.println("write how many subjects student have: ");
        int studentSubjectsNum = scanner.nextInt();
        for (int i = 0; i < studentSubjectsNum; i++) {
            System.out.println("write student subject: ");
            String subjectName = scanner.nextLine();
            stringArrayList.add(subjectName);
        }

        stringArrayList.forEach(s -> {
            if (subjectService.getByName(s) != null) {
                subjectArrayList.add(subjectService.getByName(s));
            } else {
                System.out.println("write subject ECTS number: ");
                int subjectECTS = scanner.nextInt();
                Subject subject = new Subject(s, subjectECTS);
                subjectService.create(subject);
                subjectArrayList.add(subject);
            }
        });

        return new Student(studentName, studentId, subjectArrayList);
    }
}
