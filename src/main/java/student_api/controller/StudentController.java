package student_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student_api.dto.request.StudentPatchRequest;
import student_api.dto.request.StudentRequest;
import student_api.dto.response.ApiResponse;
import student_api.dto.response.StudentResponse;
import student_api.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.CREATED.value(), "Lấy danh sách học sinh thành công", service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Lấy học sinh thành công", service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> create(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(HttpStatus.CREATED.value(), "Tạo học sinh thành công", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody(required = false) StudentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Dữ liệu yêu cầu không được để trống");
        }
        if (request.getFullName() == null || request.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên học sinh không được để trống");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên học sinh không được để trống");
        }
        if (request.getGpa() == null) {
            throw new IllegalArgumentException("Điểm GPA không được để trống");
        }
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Cập nhật học sinh thành công", service.update(id, request)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> patch(@PathVariable Long id, @RequestBody StudentPatchRequest request) {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Cập nhật một phần học sinh thành công", service.patch(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Xóa học sinh thành công", null));
    }
}