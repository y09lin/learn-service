package com.hui.day.learn.controller.params;

import lombok.Data;

import java.util.List;

@Data
public class ParagraphParams {
    private Long articleId;

    private List<String> sentenceList;
}
