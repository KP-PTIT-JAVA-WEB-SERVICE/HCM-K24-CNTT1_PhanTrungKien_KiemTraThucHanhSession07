package student_api.config;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import student_api.entity.Student;
import student_api.repository.StudentRepository;


@Component
public class DataSeeder implements CommandLineRunner {
    private final StudentRepository repository;

    public DataSeeder(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String @NonNull ... args) {
        if (repository.count() == 0) {
            repository.save(new Student(null, "Phan Trung Kiên", "kien@gmail.com", 4.0));
            repository.save(new Student(null, "Phan Trung Trung", "trung@gmail.com", 2.0));
            repository.save(new Student(null, "Phan Trung Phan", "phan@gmail.com", 3.0));
        }
    }
}