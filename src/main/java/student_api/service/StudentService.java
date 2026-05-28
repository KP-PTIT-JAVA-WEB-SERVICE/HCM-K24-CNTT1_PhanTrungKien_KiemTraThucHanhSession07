package student_api.service;


import student_api.dto.request.StudentPatchRequest;
import student_api.dto.request.StudentRequest;
import student_api.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAll();
    StudentResponse getById(Long id);
    StudentResponse create(StudentRequest request);
    StudentResponse update(Long id, StudentRequest request);
    StudentResponse patch(Long id, StudentPatchRequest request);
    void delete(Long id);
}