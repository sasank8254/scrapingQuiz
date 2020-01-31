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
    @Autowired
    ScrapingRepository scrapingRepository;


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

    @Override
    public boolean getCricketQuestions() throws IOException {
        Document document1 = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-the-icc-cricket-world-cup-1559885944-1").get();
        Elements cricketqstn = document1.select("p:contains(?)");
        Elements cricketoptA = document1.select("p:contains((a))");
        Elements cricketoptB = document1.select("p:contains((b)");
        Elements cricketoptC = document1.select("p:contains((c)");
        Elements cricketoptD = document1.select("p:contains((d)");
        Elements cricketanswer = document1.select("p:contains(Ans.)");
        FileOutputStream cricketfout=new FileOutputStream("CricketQuestionBank.csv");
        PrintStream cricketcsv=new PrintStream(cricketfout);

        for (int i=0;i<cricketqstn.size();i++){
            String stringQuestion = cricketqstn.get(i).text();
            stringQuestion=stringQuestion.substring(3);
            stringQuestion = stringQuestion.trim();
            cricketcsv.print('"'+cricketqstn.get(i).text()+'"');
            cricketcsv.print(",");
            cricketcsv.print("\n");
            String optA = cricketoptA.get(i).text();
            optA = optA.substring(3);
            optA = optA.trim();
            cricketcsv.print('"'+cricketoptA.get(i).text()+'"');
            cricketcsv.print(",");
            cricketcsv.print("\n");
            String optB = cricketoptB.get(i).text();
            optB = optB.substring(3);
            optB = optB.trim();
            cricketcsv.print('"'+cricketoptB.get(i).text()+'"');
            cricketcsv.print(",");
            cricketcsv.print("\n");
            String optC = cricketoptC.get(i).text();
            optC = optC.substring(3);
            optC = optC.trim();
            cricketcsv.print('"'+cricketoptC.get(i).text()+'"');
            cricketcsv.print(",");
            cricketcsv.print("\n");
            String optD = cricketoptD.get(i).text();
            optD = optD.substring(3);
            optD = optD.trim();
            cricketcsv.print('"'+cricketoptD.get(i).text()+'"');
            cricketcsv.print(",");
            cricketcsv.print("\n");
            String answer = cricketanswer.get(i).text();
            answer = answer.substring(4);
            answer = answer.trim();
            int len = answer.length();
            cricketcsv.print('"'+cricketanswer.get(i).text()+'"');
            cricketcsv.print(",");
            cricketcsv.print("\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("question", stringQuestion);
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setQuestionAndChoice(map);
            questions.setQuestionFormat("text");
            questions.setAnswer(answer);
            questions.setCategoryId("Cricket");
            questions.setAnswerType(1);
            scrapingRepository.save(questions);
        }

        return true;
    }

    public List<Questions> findQuestions() {
        return scrapingRepository.findAll();
    }

    public void addQuestions(Questions questions){
        scrapingRepository.save(questions);
    }



    @Override
    public boolean getLiteratureQuestions() throws IOException {


        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/articles/literature-general-knowledge-quiz-1314427586-1").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        Elements movieanswer = moviedocument.select("strong:contains(Ans:)");
        FileOutputStream moviefout = new FileOutputStream("LiteratureQuestionBank.csv");
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
            String optC = movieoptC.get(i+1).text().substring(2).trim();
            moviecsv.print('"' + movieoptC.get(i+1).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
            String optD = movieoptD.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptD.get(i).text() + '"');
            moviecsv.print(",");
            moviecsv.print("\n");
            String answer = movieanswer.get(i).text().substring(4).trim();
            int len = answer.length();
            moviecsv.print('"' + answer + '"');
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
            questions.setCategoryId("literature");
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

//    public boolean getMovieQuestions1() throws IOException {
//        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-the-indian-classical-theatre-and-drama-1549368269-1").get();
//        Elements movieqstn = moviedocument.select("p:contains(?)");
//        Elements movieoptA = moviedocument.select("p:contains(A.)");
//        Elements movieoptB = moviedocument.select("p:contains(B.)");
//        Elements movieoptC = moviedocument.select("p:contains(C.)");
//        Elements movieoptD = moviedocument.select("p:contains(D.)");
//        Elements movieanswer = moviedocument.select("p:contains(Ans:)");
//        FileOutputStream moviefout = new FileOutputStream("MovieQuestionBank.csv");
//        PrintStream moviecsv = new PrintStream(moviefout);
//        for (int i = 0; i < movieqstn.size(); i++) {
//            moviecsv.print('"' + movieqstn.get(i).text() + '"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"' + movieoptA.get(i).text() + '"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"' + movieoptB.get(i).text() + '"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"' + movieoptC.get(i).text() + '"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"' + movieoptD.get(i).text() + '"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//            moviecsv.print('"' + movieanswer.get(i).text() + '"');
//            moviecsv.print(",");
//            moviecsv.print("\n");
//        }
//        moviefout.close();
//        System.out.println(moviedocument.title());
//        return false;
//    }
}
