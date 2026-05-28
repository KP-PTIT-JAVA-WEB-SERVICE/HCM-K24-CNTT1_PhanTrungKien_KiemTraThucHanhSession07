package student_api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @NotBlank(message = "Tên học sinh không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email phải đúng định dạng")
    private String email;

    @NotNull(message = "Gpa không được bỏ trống")
    @Min(value = 0, message = "Gpa không được âm")
    @Max(value = 4, message = "Gpa không được lớn hơn 4")
    private Double gpa;
}