package com.jinan159.study.springboot.web;

import com.jinan159.study.springboot.web.dto.RealProfiles;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        List<String> acviteProfiles = Arrays.asList(env.getActiveProfiles());
        List<String> realProfiles = Arrays.stream(RealProfiles.values())
                                            .map(Enum::toString)
                                            .collect(Collectors.toList());
        String defaultProfile = acviteProfiles.isEmpty() ? RealProfiles.DEFAULT.toString() : acviteProfiles.get(0);

        return acviteProfiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
