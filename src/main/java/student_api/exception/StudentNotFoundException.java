package student_api.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Không tìm thấy học sinh có id: " + id);
    }
}