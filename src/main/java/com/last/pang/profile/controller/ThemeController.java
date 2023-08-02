package com.last.pang.profile.controller;

import com.last.pang.profile.dto.ThemeDto;
import com.last.pang.profile.entity.Theme;
import com.last.pang.profile.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/theme")
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping
    public ResponseEntity<?> getThemeList() {
        List<Theme> themeList = themeService.getThemeList();
        return ResponseEntity.status(HttpStatus.OK).body(themeList);
    }


    @PostMapping
    public ResponseEntity<?> createTheme(ThemeDto dto) {
        themeService.createTheme(dto);
        return ResponseEntity.status(HttpStatus.OK).body("테마 생성 성공");
    }

    @PutMapping
    public ResponseEntity<?> updateTheme(ThemeDto dto) {
        themeService.updateTheme(dto);
        return ResponseEntity.status(HttpStatus.OK).body("테마 수정 성공");
    }

    @DeleteMapping("/{themeId}")
    public ResponseEntity<?> deleteTheme(@PathVariable Long themeId) {
        themeService.deleteTheme(themeId);
        return ResponseEntity.status(HttpStatus.OK).body("테마 삭제 성공");
    }

}
