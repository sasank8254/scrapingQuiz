package com.example.ScrapingProject.service;

import com.example.ScrapingProject.entity.Questions;

import java.io.IOException;
import java.util.List;

public interface ScrapingService {
    boolean getMovieQuestions() throws IOException;
    boolean getCricketQuestions() throws IOException;
    boolean getLiteratureQuestions() throws IOException;
    boolean getLiteratureQuestions1() throws IOException;
    boolean getMovieQuestions1() throws IOException;
    boolean getCricketQuestions1() throws IOException;
    boolean getCricketQuestions2() throws IOException;
    boolean getCricketQuestions3() throws IOException;
    boolean getFootballQuestions() throws IOException;
    boolean getSportsQuestions() throws IOException;
    boolean getSportsQuestions1() throws IOException;
    boolean getTennisQuestions() throws IOException;
    boolean getTennisQuestions1() throws IOException;
    boolean getTennisQuestions2() throws IOException;
    boolean getTennisQuestions3() throws IOException;
    boolean getHockeyQuestions() throws IOException;
    boolean getBollywoodQuestions() throws IOException;
    boolean getHollywoodQuestions() throws IOException;
    boolean getHollywoodQuestions1() throws IOException;
    List<Questions> findQuestions();
    List<Questions> findByCategory(String string);
    void addQuestions(Questions questions);
}
