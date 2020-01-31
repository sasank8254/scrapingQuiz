package com.example.ScrapingProject.service;

import com.example.ScrapingProject.entity.Questions;

import java.io.IOException;
import java.util.List;

public interface ScrapingService {
    boolean getMovieQuestions() throws IOException;
    boolean getCricketQuestions() throws IOException;
    boolean getLiteratureQuestions() throws IOException;
    List<Questions> findQuestions();
    void addQuestions(Questions questions);
}
