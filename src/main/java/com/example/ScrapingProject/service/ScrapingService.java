package com.example.ScrapingProject.service;

import com.example.ScrapingProject.entity.Questions;

import java.io.IOException;
import java.util.List;

public interface ScrapingService {
    boolean getMovieQuestions() throws IOException;
//    String getTitle() throws IOException;
//    boolean getMovieQuestions1() throws IOException;
    List<Questions> findQuestions();
}
