package student_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student_api.dto.request.StudentPatchRequest;
import student_api.dto.request.StudentRequest;
import student_api.dto.response.StudentResponse;
import student_api.entity.Student;
import student_api.exception.StudentNotFoundException;
import student_api.mapper.StudentMapper;
import student_api.repository.StudentRepository;
import student_api.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<StudentResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public StudentResponse getById(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return mapper.toResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {
        Student student = mapper.toEntity(request);
        Student saved = repository.save(student);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        Student saved = repository.save(student);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public StudentResponse patch(Long id, StudentPatchRequest request) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        
        if (request.getFullName() != null) {
            if (request.getFullName().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên học sinh không được để trống");
            }
            student.setFullName(request.getFullName());
        }

        if (request.getEmail() != null) {
            if (request.getEmail().trim().isEmpty()) {
                throw new IllegalArgumentException("Email học sinh không được để trống");
            }
            student.setEmail(request.getEmail());
        }

        if (request.getGpa() != null) {
            student.setGpa(request.getGpa());
        }

        
        Student saved = repository.save(student);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        repository.delete(student);
    }
}