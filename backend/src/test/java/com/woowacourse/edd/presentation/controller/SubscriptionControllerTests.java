package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.LoginRequestDto;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.woowacourse.edd.exceptions.UnauthenticatedException.UNAUTHENTICATED_MESSAGE;
import static com.woowacourse.edd.exceptions.UserNotFoundException.USER_NOT_FOUND_MESSAGE;
import static com.woowacourse.edd.presentation.controller.UserController.USER_URL;

public class SubscriptionControllerTests extends BasicControllerTests {

    @Test
    void subscribe() {
        UserSaveRequestDto subscriber = new UserSaveRequestDto("conas", "bonghee@gmail.com", "p@ssW0rd");
        UserSaveRequestDto subscribed = new UserSaveRequestDto("jm", "jayem@gmail.com", "p@ssW0rd");
        String url = signUp(subscribed).getResponseHeaders().getLocation().toASCIIString();
        signUp(subscriber);

        LoginRequestDto loginRequestDto = new LoginRequestDto("bonghee@gmail.com", "p@ssW0rd");
        String sid = getLoginCookie(loginRequestDto);

        executePost(url + "/subscribe")
            .cookie(COOKIE_JSESSIONID, sid)
            .exchange()
            .expectStatus().isCreated();
    }

    @Test
    void subscribe_fail_without_login() {
        UserSaveRequestDto subscribed = new UserSaveRequestDto("ethan", "ethan@gmail.com", "p@ssW0rd");
        String url = signUp(subscribed).getResponseHeaders().getLocation().toASCIIString();

        assertFailUnauthorized(webTestClient.post().uri(url + "/subscribe")
            .exchange(), UNAUTHENTICATED_MESSAGE);
    }

    @Test
    void find_subscribers() {
        UserSaveRequestDto subscriber1 = new UserSaveRequestDto("conas", "conas2@gmail.com", "p@ssW0rd");
        UserSaveRequestDto subscriber2 = new UserSaveRequestDto("heebong", "heebong@gmail.com", "p@ssW0rd");
        UserSaveRequestDto subscribed = new UserSaveRequestDto("jm", "jayem2@gmail.com", "p@ssW0rd");
        signUp(subscriber1);
        signUp(subscriber2);

        String url = signUp(subscribed).getResponseHeaders().getLocation().toASCIIString();

        LoginRequestDto loginRequestDto = new LoginRequestDto("conas2@gmail.com", "p@ssW0rd");
        String sid = getLoginCookie(loginRequestDto);

        executePost(url + "/subscribe")
            .cookie(COOKIE_JSESSIONID, sid)
            .exchange();

        loginRequestDto = new LoginRequestDto("heebong@gmail.com", "p@ssW0rd");
        sid = getLoginCookie(loginRequestDto);

        executePost(url + "/subscribe")
            .cookie(COOKIE_JSESSIONID, sid)
            .exchange();

        executeGet(url + "/count-subscribers")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.count").isEqualTo(2);
    }

    @DisplayName("구독자가 존재하지 않는데, 구독자 수를 조회하는 경우")
    @Test
    void find_subscribers_without_subscribed() {
        String url = USER_URL + "/" +Integer.MAX_VALUE+"/count-subscribers";
        assertFailNotFound(executeGet(url).exchange(),USER_NOT_FOUND_MESSAGE);
    }
}
