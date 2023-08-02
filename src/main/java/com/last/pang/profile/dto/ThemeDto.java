package com.last.pang.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThemeDto {
    private Long id;
    private String themeName;

    private String s3Key;
    private String s3Path;
    private String resourcePath;

}
