package com.example.ScrapingProject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QuestionsDTO {

    private String questionId;
    private String questionFormat;
    private String answers;
    private String categoryId;
    private Integer questionType;
    private String questionText;
    private Map<String, String> options;
    private String difficultyLevel;
    private String urlAttachment;
}
