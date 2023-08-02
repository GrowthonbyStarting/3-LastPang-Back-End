package com.last.pang.user.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Name {
    private String firstname;
    private String lastname;
}
