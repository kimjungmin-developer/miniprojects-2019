package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.LoginRequestDto;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.StatusAssertions;

import static com.woowacourse.edd.exceptions.UnauthenticatedException.UNAUTHENTICATED_MESSAGE;

public class SubscriptionControllerTests extends BasicControllerTests {

    @Test
    void subscribe() {
        UserSaveRequestDto subscriber = new UserSaveRequestDto("conas", "conas@gmail.com", "p@ssW0rd");
        UserSaveRequestDto subscribed = new UserSaveRequestDto("jm", "jayem@gmail.com", "p@ssW0rd");
        String url = signUp(subscribed).getResponseHeaders().getLocation().toASCIIString();
        signUp(subscriber);

        LoginRequestDto loginRequestDto = new LoginRequestDto("conas@gmail.com", "p@ssW0rd");
        String sid = getLoginCookie(loginRequestDto);

        webTestClient.post().uri(url + "/subscribe")
            .cookie(COOKIE_JSESSIONID, sid)
            .exchange()
            .expectStatus().isCreated();
    }

    @Test
    void subscribe_fail_without_login() {
        UserSaveRequestDto subscribed = new UserSaveRequestDto("ethan", "ethan@gmail.com", "p@ssW0rd");
        String url = signUp(subscribed).getResponseHeaders().getLocation().toASCIIString();

        StatusAssertions statusAssertions = webTestClient.post().uri(url + "/subscribe")
            .exchange()
            .expectStatus();
        assertFailUnauthorized(statusAssertions, UNAUTHENTICATED_MESSAGE);
    }

    @Test
    void find_subscribers() {
        UserSaveRequestDto subscriber1 = new UserSaveRequestDto("conas", "conas@gmail.com", "p@ssW0rd");
        UserSaveRequestDto subscriber2 = new UserSaveRequestDto("heebong", "heebong@gmail.com", "p@ssW0rd");
        UserSaveRequestDto subscribed = new UserSaveRequestDto("jm", "jayem@gmail.com", "p@ssW0rd");
        signUp(subscriber1);
        signUp(subscriber2);

        String url = signUp(subscribed).getResponseHeaders().getLocation().toASCIIString();

        LoginRequestDto loginRequestDto = new LoginRequestDto("conas@gmail.com", "p@ssW0rd");
        String sid = getLoginCookie(loginRequestDto);

        webTestClient.post().uri(url + "/subscribe")
            .cookie(COOKIE_JSESSIONID, sid)
            .exchange();

        loginRequestDto = new LoginRequestDto("heebong@gmail.com", "p@ssW0rd");
        sid = getLoginCookie(loginRequestDto);

        webTestClient.post().uri(url + "/subscribe")
            .cookie(COOKIE_JSESSIONID, sid)
            .exchange();

        webTestClient.get().uri(url + "/count-subscribers")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.count").isEqualTo(2);
    }

    @DisplayName("구독자가 존재하지 않는데, 구독 신청을 하는 경우")
    @Test
    void find_subscribers_without_subscribed() {
        String url = "/v1/users/"+Integer.MAX_VALUE + "count-subscribers";
            webTestClient.get().uri(url)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
