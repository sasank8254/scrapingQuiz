package com.example.ScrapingProject.service.impl;

import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.repository.ScrapingRepository;
import com.example.ScrapingProject.service.ScrapingService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScrapingServiceImpl implements ScrapingService {
    String url;
    @Autowired
    ScrapingRepository scrapingRepository;

//    public String getTitle() throws IOException {
//        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-the-indian-cinema-1549443229-1").get();
//        System.out.println(moviedocument.title());
//        return moviedocument.title();
//    }

    @Override
    public boolean getMovieQuestions() throws IOException {


        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-the-indian-cinema-1549443229-1").get();

        Elements movieqstn = moviedocument.select("p:contains(?)");
        Elements movieoptA = moviedocument.select("p:matches(A. )");
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        System.out.println(movieoptB.size());
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        System.out.println(movieoptC.size());
        Elements movieoptD = moviedocument.select("p:matches(D. )");
        System.out.println(movieoptD.size());
        Elements movieanswer = moviedocument.select("p:contains(Ans:)");
        FileOutputStream moviefout = new FileOutputStream("MovieQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {

            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
            //todo: move three line into one line
            String optA = movieoptA.get(i).text().substring(2).trim();
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
            String answer = movieanswer.get(i).text().substring(4).trim();
            int len = answer.length();
            moviecsv.print('"' + movieanswer.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("question", stringquestion);
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setQuestionAndChoice(map);
            questions.setQuestionFormat("text");
            questions.setAnswer(answer);
            questions.setCategoryId("movie");
            if (len == 1) {
                questions.setAnswerType(1);
            } else {
                questions.setAnswerType(2);
            }
            scrapingRepository.save(questions);
        }
        moviefout.close();
        return false;
    }

    public List<Questions> findQuestions() {
        return scrapingRepository.findAll();
    }

//    public boolean getMovieQuestions1() throws IOException {
//        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-the-indian-classical-theatre-and-drama-1549368269-1").get();
//        Elements movieqstn = moviedocument.select("p:contains(?)");
//        Elements movieoptA = moviedocument.select("p:contains(A.)");
//        Elements movieoptB = moviedocument.select("p:contains(B.)");
//        Elements movieoptC = moviedocument.select("p:contains(C.)");
//        Elements movieoptD = moviedocument.select("p:contains(D.)");
//        Elements movieanswer = moviedocument.select("p:contains(Ans:)");
//        FileOutputStream moviefout=new FileOutputStream("MovieQuestionBank.csv");
//        PrintStream moviecsv=new PrintStream(moviefout);
//        for (int i=0; i < movieqstn.size(); i++) {
//            moviecsv.print('"'+movieqstn.get(i).text()+'"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"'+movieoptA.get(i).text()+'"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"'+movieoptB.get(i).text()+'"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"'+movieoptC.get(i).text()+'"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"'+movieoptD.get(i).text()+'"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"'+movieanswer.get(i).text()+'"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//        }
//        moviefout.close();
//        System.out.println(moviedocument.title());
//        return false;
//    ?
}
