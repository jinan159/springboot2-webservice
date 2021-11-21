package com.jinan159.study.springboot.web;

import com.jinan159.study.springboot.web.dto.RealProfiles;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void real_profile이_조회된다() {
        // given
        RealProfiles expectedProfile = RealProfiles.REAL;
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile.toString());
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String activeProfile = controller.profile();

        // then
        assertThat(activeProfile).isEqualTo(expectedProfile.toString());
    }

    @Test
    public void real_profile이_없으면_첫_번째가_조회된다() {
        // given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String activeProfile = controller.profile();

        // then
        assertThat(activeProfile).isEqualTo(expectedProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다() {
        // given
        RealProfiles expectedProfile = RealProfiles.DEFAULT;
        MockEnvironment env = new MockEnvironment();

        ProfileController controller = new ProfileController(env);

        // when
        String activeProfile = controller.profile();

        // then
        assertThat(activeProfile).isEqualTo(expectedProfile.toString());
    }
}
