package com.last.pang.common.file.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.last.pang.common.BaseTimeEntity;
import com.last.pang.profile.entity.Profile;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileKey;

    @Column(nullable = false)
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId")
    @JsonIgnore
    private Profile profile;

}
