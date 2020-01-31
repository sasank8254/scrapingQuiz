package com.example.ScrapingProject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Document
public class Questions {
    @Id
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
///Answer type 1 - single correct
///Answer type 2- multi correct
///Answer type 3 - arrange the order