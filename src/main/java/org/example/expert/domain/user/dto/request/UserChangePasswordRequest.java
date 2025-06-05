package org.example.expert.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordRequest {

    @NotBlank
    @Size(min = 8)
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[A-Z]).*$",
            message = "비밀번호에는 숫자와 대문자가 각각 하나 이상 포함되어야 합니다."
    )
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
