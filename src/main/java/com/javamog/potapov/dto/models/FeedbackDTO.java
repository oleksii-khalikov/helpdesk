package com.javamog.potapov.dto.models;

import lombok.Data;

@Data
public class FeedbackDTO {
    private Long id;
    private Integer rate;
    private String text;
}
