package com.barash.spotlight.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponse {

    private Long          id;
    private String        name;
    private String        email;
    private String        subject;
    private String        message;
    private boolean       read;
    private LocalDateTime createdAt;
}
