package com.barash.spotlight.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {

    private Long          id;
    private String        title;
    private String        description;
    private String        techStack;
    private String        githubUrl;
    private String        liveUrl;
    private String        imageUrl;
    private boolean       featured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}