package com.last.pang.profile.repository;

import com.last.pang.profile.entity.Profile;
import com.last.pang.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByOwner(User user);
}
