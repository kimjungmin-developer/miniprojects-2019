package com.woowacourse.edd.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private final SubscriptionInternalService subscriptionInternalService;

    @Autowired
    public SubscriptionService(SubscriptionInternalService subscriptionInternalService) {
        this.subscriptionInternalService = subscriptionInternalService;
    }

    public void subscribe(Long subscribedId, Long id) {
        subscriptionInternalService.save(subscribedId, id);
    }
}
