package com.last.pang.user.service;

import com.last.pang.common.file.entity.Image;
import com.last.pang.common.file.service.AwsS3Service;
import com.last.pang.config.security.PrincipalDetails;
import com.last.pang.user.dto.ProfileImageDto;
import com.last.pang.user.dto.UserDto;
import com.last.pang.user.entity.User;
import com.last.pang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AwsS3Service awsS3Service;


    /**
     * 회원가입 서비스 메서드
     *
     * @param dto 회원 dto
     */
    @Transactional
    public void join(UserDto dto) {
        System.out.println(dto.getPassword());
        String encPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        User user = dto.toEntity(encPassword);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDto getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 없습니다."));
        return UserDto.from(user);
    }

    @Transactional
    public void updateProfile(Authentication authentication, UserDto dto) {
        String username = ((PrincipalDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 없습니다."));
        String encPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        user.update(dto, encPassword);
    }

    @Transactional
    public Image updateProfileImage(Authentication authentication, ProfileImageDto dto) throws IOException {
        User loginUser = ((PrincipalDetails) authentication.getPrincipal()).getUser();
        Image uploadResult = awsS3Service.upload(dto.getProfileImage(), "profile");
        userRepository.save(loginUser);
        return uploadResult;
    }


}
