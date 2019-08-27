package com.woowacourse.edd.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidSubscriptionException extends ErrorResponseException {

    private static final String INVALID_SUBSCRIPTION_MESSAGE = "자기 자신을 구독할 수 없습니다.";

    public InvalidSubscriptionException() {
        super(INVALID_SUBSCRIPTION_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
