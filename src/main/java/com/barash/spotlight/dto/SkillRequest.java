package com.barash.spotlight.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Proficiency must be between 0 and 100")
    @Max(value = 100, message = "Proficiency must be between 0 and 100")
    private Integer proficiency;

    private String iconUrl;

    private int displayOrder;
}
