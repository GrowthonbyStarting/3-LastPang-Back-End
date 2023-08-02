package com.last.pang.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SnsLink {
    private String facebook;
    private String instagram;
    private String blog;
    private String youtube;
    private String twitter;
}
