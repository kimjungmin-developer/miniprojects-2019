package com.woowacourse.edd.application.service;

import com.woowacourse.edd.domain.Subscription;
import com.woowacourse.edd.domain.User;
import com.woowacourse.edd.exceptions.InvalidSubscriptionException;
import com.woowacourse.edd.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionInternalService {

    private final UserInternalService userInternalService;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionInternalService(UserInternalService userInternalService, SubscriptionRepository subscriptionRepository) {
        this.userInternalService = userInternalService;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription save(Long subscribedId, Long id) {
        if (subscribedId == id) {
            throw new InvalidSubscriptionException();
        }
        User subscribed = userInternalService.findById(subscribedId);
        User subscriber = userInternalService.findById(id);
        Subscription subscription = new Subscription(subscriber, subscribed);
        return subscriptionRepository.save(subscription);
    }
}
