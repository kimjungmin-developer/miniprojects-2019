package com.woowacourse.edd.application.converter;

import com.woowacourse.edd.application.response.SubscriptionCountResponse;

public class SubscriptionConverter {

    public static SubscriptionCountResponse toResponse(int count) {
        return new SubscriptionCountResponse(count);
    }
}
