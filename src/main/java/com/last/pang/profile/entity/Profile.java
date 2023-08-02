package com.last.pang.profile.entity;

import com.last.pang.common.BaseTimeEntity;
import com.last.pang.common.file.entity.Image;
import com.last.pang.profile.dto.ProfileDto;
import com.last.pang.user.entity.PrivacyInfo;
import com.last.pang.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Profile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "profile", orphanRemoval = true)
    @Builder.Default
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Embedded
    private PrivacyInfo privacyInfo;

    @Embedded
    private SnsLink snsLink;

    @OneToOne
    private Theme theme;

    public void updateProfile(ProfileDto profileDto, Theme theme) {
        this.profileUrl = profileDto.getProfileUrl();
        this.profileType = ProfileType.valueOf(profileDto.getProfileType());
        this.privacyInfo = PrivacyInfo.builder()
                .name(profileDto.getName())
                .description(profileDto.getDescription())
                .email(profileDto.getEmail())
                .phone(profileDto.getPhone())
                .build();
        this.snsLink = SnsLink.builder()
                .blog(profileDto.getBlog())
                .facebook(profileDto.getFacebookLink())
                .instagram(profileDto.getInstagramLink())
                .twitter(profileDto.getTwitter())
                .youtube(profileDto.getYoutube())
                .build();
        this.theme = theme;
    }
}
