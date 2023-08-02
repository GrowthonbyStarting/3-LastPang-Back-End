package com.last.pang.profile.service;

import com.amazonaws.services.ec2.model.PricingDetail;
import com.last.pang.common.file.entity.Image;
import com.last.pang.config.security.PrincipalDetails;
import com.last.pang.profile.dto.ProfileDto;
import com.last.pang.profile.entity.Profile;
import com.last.pang.profile.entity.ProfileType;
import com.last.pang.profile.entity.SnsLink;
import com.last.pang.profile.entity.Theme;
import com.last.pang.profile.repository.ProfileRepository;
import com.last.pang.profile.repository.ThemeRepository;
import com.last.pang.user.entity.PrivacyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ThemeRepository themeRepository;
    private final ProfileRepository profileRepository;


    @Transactional(readOnly = true)
    public List<Profile> getProfileList(PrincipalDetails principalDetails) {
        return profileRepository.findByOwner(principalDetails.getUser());
    }


    @Transactional
    public void createProfile(ProfileDto profileDto) {
        SnsLink snsLink = SnsLink.builder()
                .blog(profileDto.getBlog())
                .facebook(profileDto.getFacebookLink())
                .instagram(profileDto.getInstagramLink())
                .twitter(profileDto.getTwitter())
                .youtube(profileDto.getYoutube())
                .build();
        PrivacyInfo privacyInfo = PrivacyInfo.builder()
                .name(profileDto.getName())
                .description(profileDto.getDescription())
                .email(profileDto.getEmail())
                .phone(profileDto.getPhone())
                .build();

        Theme theme =
                themeRepository.findByThemeName(profileDto.getTheme())
                        .orElseThrow(() -> new EntityNotFoundException("해당 테마가 존재하지 않습니다."));

        Profile profile = Profile.builder()
                .privacyInfo(privacyInfo)
                .snsLink(snsLink)
                .theme(theme)
                .profileType(ProfileType.valueOf(profileDto.getProfileType()))
//                .images(imageList)
                .build();

        profileRepository.save(profile);
    }

    @Transactional
    public void updateProfile(ProfileDto profileDto) {
        Profile profile = profileRepository.findById(profileDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("해당 프로필이 존재하지 않습니다."));

        Theme theme = themeRepository.findByThemeName(profileDto.getTheme())
                .orElseThrow(() -> new EntityNotFoundException("해당 테마가 존재하지 않습니다."));

        profile.updateProfile(profileDto, theme);
    }

    @Transactional
    public List<Image> deleteProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로필이 존재하지 않습니다."));
        profileRepository.delete(profile);
        return profile.getImages();
    }
}
