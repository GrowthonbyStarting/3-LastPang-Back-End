package com.last.pang.profile.dto;

import com.last.pang.profile.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String profileUrl;
    private String profileType;

    private String name;
    private String description;
    private String email;
    private String phone;

    private String instagramLink;
    private String facebookLink;
    private String blog;
    private String youtube;
    private String twitter;

    private String theme;
    //    private List<MultipartFile> images;


}
