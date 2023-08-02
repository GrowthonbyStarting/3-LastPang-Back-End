package com.last.pang.user.entity;

import com.last.pang.common.BaseTimeEntity;
import com.last.pang.profile.entity.Profile;
import com.last.pang.user.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String nickname;

    private String password;


    @Column(unique = true)
    private String email;

    private Purpose purpose;
    private Name name;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    @Enumerated(EnumType.STRING)
    private JobGroup jobGroup;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Profile> profileList;

    public void update(UserDto dto, String encPassword) {
        this.nickname = dto.getNickname();
        this.email = dto.getEmail();
        this.birthday = dto.getBirthday();
        this.password = encPassword;
    }

}
