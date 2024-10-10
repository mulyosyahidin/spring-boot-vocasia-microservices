package com.vocasia.enrollment.entity.client;

import com.vocasia.enrollment.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "student";

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

}
