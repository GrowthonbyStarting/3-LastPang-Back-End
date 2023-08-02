package com.last.pang.profile.entity;

import com.last.pang.common.BaseTimeEntity;
import com.last.pang.profile.dto.ThemeDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Theme extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String themeName;

    private String s3Key;
    private String s3Path;
    private String resourcePath;

    public void updateTheme(ThemeDto dto) {
        this.themeName = dto.getThemeName();
        this.s3Key = dto.getS3Key();
        this.s3Path = dto.getS3Path();
        this.resourcePath = dto.getResourcePath();
    }
}
