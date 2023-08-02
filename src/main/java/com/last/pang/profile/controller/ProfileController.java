package com.last.pang.profile.controller;

import com.last.pang.common.file.entity.Image;
import com.last.pang.common.file.service.AwsS3Service;
import com.last.pang.common.file.service.ImageService;
import com.last.pang.config.security.PrincipalDetails;
import com.last.pang.profile.dto.ProfileDto;
import com.last.pang.profile.entity.Profile;
import com.last.pang.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class ProfileController {
    private final ProfileService profileService;
//    private final AwsS3Service awsS3Service;
//    private final ImageService imageService;


    @GetMapping("/")
    public ResponseEntity<?> getProfileList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Profile> profileList = profileService.getProfileList(principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(profileList);
    }

    @PostMapping("/")
    public ResponseEntity<?> createProfile(ProfileDto profileDto) {
//        List<Image> imageList = awsS3Service.uploadFileList(profileDto.getImages(), "profile");
//        imageService.saveImage(imageList);
//        profileService.createProfile(profileDto, imageList);
        profileService.createProfile(profileDto);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 생성 성공");
    }

    @PutMapping("/")
    public ResponseEntity<?> updateProfile(ProfileDto profileDto) {
//        List<Image> imageList = awsS3Service.uploadFileList(profileDto.getImages(), "profile");
//        imageService.saveImage(imageList);
        profileService.updateProfile(profileDto);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 수정 성공");
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long profileId) {
        List<Image> imageList = profileService.deleteProfile(profileId);
//        awsS3Service.removeImageList(imageList);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 삭제 성공");
    }

}
