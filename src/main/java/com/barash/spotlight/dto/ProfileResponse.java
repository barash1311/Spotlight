package com.barash.spotlight.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private String name;
    private String title;
    private String bio;
    private String githubUrl;
    private String linkedinUrl;
    private String email;
}
