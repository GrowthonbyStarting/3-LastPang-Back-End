package com.last.pang.user.controller;

import com.last.pang.common.file.entity.Image;
import com.last.pang.user.dto.ProfileImageDto;
import com.last.pang.user.dto.UserDto;
import com.last.pang.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserDto dto) {
        userService.join(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        UserDto dto = userService.getProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(Authentication authentication, @RequestBody UserDto dto) {
        userService.updateProfile(authentication, dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "/profile/image")
    public ResponseEntity<?> updateProfileImage(
            Authentication authentication, ProfileImageDto dto) throws IOException {
        Image result = userService.updateProfileImage(authentication, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}