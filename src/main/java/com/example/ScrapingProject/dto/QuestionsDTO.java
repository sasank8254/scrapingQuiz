package com.example.ScrapingProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionsDTO {
    String questionId;
    String questionFormat;
    String answer;
    String difficultyLevel;
    String categoryId;
    String url;
    Integer answerType;
}
