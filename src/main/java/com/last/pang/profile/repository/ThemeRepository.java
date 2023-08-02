package com.last.pang.profile.repository;

import com.last.pang.profile.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Optional<Theme> findByThemeName(String themeName);
}
