package com.last.pang.profile.service;

import com.last.pang.profile.dto.ThemeDto;
import com.last.pang.profile.entity.Theme;
import com.last.pang.profile.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    @Transactional(readOnly = true)
    public List<Theme> getThemeList() {
        return themeRepository.findAll();
    }

    @Transactional
    public void createTheme(ThemeDto dto) {
        Theme theme = Theme.builder()
                .themeName(dto.getThemeName())
                .s3Key(dto.getS3Key())
                .s3Path(dto.getS3Path())
                .resourcePath(dto.getResourcePath())
                .build();
        themeRepository.save(theme);
    }

    @Transactional
    public void updateTheme(ThemeDto dto) {
        Theme theme = themeRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 테마가 없습니다. id=" + dto.getId()));
        theme.updateTheme(dto);
    }

    @Transactional
    public void deleteTheme(Long themeId) {
        themeRepository.deleteById(themeId);
    }

}
