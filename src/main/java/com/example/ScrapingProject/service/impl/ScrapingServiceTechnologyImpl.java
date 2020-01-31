package com.example.ScrapingProject.service.impl;

import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.repository.ScrapingRepository;
import com.example.ScrapingProject.service.ScrapingServiceTechnology;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
@Service
public class ScrapingServiceTechnologyImpl implements ScrapingServiceTechnology {

    @Autowired
    ScrapingRepository scrapingRepository;
    @Override
    public boolean getTechnologyQuestions() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/current-gk-science-technology-questions-new-developments-1571480317-1").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("li:matches(A. )");
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("li:matches(B. )");
        Elements movieoptC = moviedocument.select("li:matches(C. )");
        Elements movieoptD = moviedocument.select("li:matches(D. )");
        Elements movieanswer = moviedocument.select("strong:contains(Ans.)");
        FileOutputStream moviefout = new FileOutputStream("TechnologyQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
            //todo: move three line into one line
//            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptA.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptB.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptC.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
            String optD = movieoptD.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptD.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
//            String answer = movieanswer.get(i).text().substring(4).trim();
//            int len = answer.length();
            moviecsv.print('"' + movieanswer.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");

//            Questions questions = new Questions();
//
//
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("question", stringquestion);
//            map.put("optionA", optA);
//            map.put("optionB", optB);
//            map.put("optionC", optC);
//            map.put("optionD", optD);
//
//
//            questions.setQuestionAndChoice(map);
//            questions.setQuestionFormat("text");
//            questions.setAnswer(answer);
//            questions.setCategoryId("movie");
//            if (len == 1) {
//                questions.setAnswerType(1);
//            } else {
//                questions.setAnswerType(2);
//            }
//            scrapingRepository.save(questions);
        }
        moviefout.close();
        return true;
    }
}
