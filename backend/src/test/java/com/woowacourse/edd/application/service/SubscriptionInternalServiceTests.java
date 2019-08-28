package com.woowacourse.edd.application.service;

import com.woowacourse.edd.domain.Subscription;
import com.woowacourse.edd.domain.User;
import com.woowacourse.edd.exceptions.InvalidSubscriptionException;
import com.woowacourse.edd.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class SubscriptionInternalServiceTests {

    private User subscriber;
    private User subscribed;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private UserInternalService userInternalService;

    @InjectMocks
    private SubscriptionInternalService subscriptionInternalService;

    @BeforeEach
    void setUp() {
        subscriber = new User("conas", "conas@gmail.com", "p@ssW0rd");
        subscribed = new User("JM", "jm@gmail.com", "p@ssW0rd");
    }

    @Test
    void save() {
        when(userInternalService.findById(1L)).thenReturn(subscriber);
        when(userInternalService.findById(2L)).thenReturn(subscribed);
        when(subscriptionRepository.save(any())).thenReturn(new Subscription(subscriber, subscribed));

        Subscription subscription = subscriptionInternalService.save(2L, 1L);

        assertThat(subscriber.getName()).isEqualTo(subscription.getSubscriber().getName());
        assertThat(subscriber.getEmail()).isEqualTo(subscription.getSubscriber().getEmail());
    }

    @Test
    void save_fail_same_id() {
        assertThrows(InvalidSubscriptionException.class, () -> subscriptionInternalService.save(3L, 3L));
    }
}
