package com.woowacourse.edd.application.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserSaveRequestDto {

    public static final String INVALID_NAME_MESSAGE = "이름은 2자이상 16자이하의 영문이어야 합니다.";
    public static final String INVALID_EMAIL_MESSAGE = "올바르지 않은 이메일형식 입니다.";
    public static final String INVALID_PASSWORD_MESSAGE = "비밀번호는 8자이상의 영문 대,소문자, 특수문자의 조합이여야 합니다.";
    public static final String INVALID_PASSWORD_CONFIRM_MESSAGE = "비밀번호 확인이 일치하지 않습니다.";

    public static final String USER_NAME_PATTERN = "^([A-Za-z가-힣]{2,16})$";
    public static final String USER_PASSWORD_PATTERN = "^.*(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";

    @Pattern(regexp = USER_NAME_PATTERN, message = INVALID_NAME_MESSAGE)
    private String name;

    @Email(message = INVALID_EMAIL_MESSAGE)
    @Size(min = 1, message = INVALID_EMAIL_MESSAGE)
    private String email;

    @Pattern(regexp = USER_PASSWORD_PATTERN, message = INVALID_PASSWORD_MESSAGE)
    private String password;

    private String passwordConfirm;

    private UserSaveRequestDto() {
    }

    public UserSaveRequestDto(String name, String email, String password, String passwordConfirm) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    @AssertTrue(message = INVALID_PASSWORD_CONFIRM_MESSAGE)
    private boolean isPasswordConfirm() {
        return password.equals(passwordConfirm);
    }
}
