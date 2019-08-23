package com.woowacourse.edd.application.converter;

import com.woowacourse.edd.application.dto.UserRequestDto;
import com.woowacourse.edd.application.response.LoginUserResponse;
import com.woowacourse.edd.application.response.SessionUser;
import com.woowacourse.edd.application.response.UserResponse;
import com.woowacourse.edd.domain.User;

public class UserConverter {

    public static User toSaveEntity(UserRequestDto userSaveRequestDto) {
        return new User(userSaveRequestDto.getName(), userSaveRequestDto.getEmail(), userSaveRequestDto.getPassword());
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public static SessionUser toSessionUser(User user) {
        return new SessionUser(user.getId());
    }

    public static LoginUserResponse toLoginUserResponse(User user) {
        return new LoginUserResponse(user.getId(), user.getName());
    }
}
