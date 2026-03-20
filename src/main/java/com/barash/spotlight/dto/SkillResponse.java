package com.barash.spotlight.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillResponse {

    private Long          id;
    private String        name;
    private String        category;
    private Integer       proficiency;
    private String        iconUrl;
    private int           displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
