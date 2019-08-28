package com.woowacourse.edd.repository;

import com.woowacourse.edd.domain.Subscription;
import com.woowacourse.edd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscribedAndSubscriber(User subscribed, User subscriber);

    List<Subscription> findAllBySubscribed(User subscribed);
}
