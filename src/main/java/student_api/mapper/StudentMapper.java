package student_api.mapper;

import org.springframework.stereotype.Component;
import student_api.dto.request.StudentRequest;
import student_api.dto.response.StudentResponse;
import student_api.entity.Student;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        return new Student(null, request.getFullName(), request.getEmail(), request.getGpa());
    }

    public StudentResponse toResponse(Student student) {
        return new StudentResponse(student.getId(), student.getFullName(), student.getEmail(), student.getGpa());
    }
}