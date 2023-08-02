package com.last.pang.common;

import com.last.pang.user.entity.User;
import com.last.pang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class UtilController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder pwEncoder;

    @PostConstruct
    public void autoJoin() {
        User user = User.builder()
                .username("test")
                .password(pwEncoder.encode("1234"))
                .email("asdf")
                .nickname("test")
                .build();
        userRepository.save(user);
    }

}
