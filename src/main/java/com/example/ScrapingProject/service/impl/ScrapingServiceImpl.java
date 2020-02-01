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
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        Elements movieoptD = moviedocument.select("p:matches(D. )");
        Elements movieanswer = moviedocument.select("p:contains(Ans:)");
        FileOutputStream moviefout = new FileOutputStream("MovieQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {

            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

            //todo: move three line into one line
            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptA.get(i).text() + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptB.get(i).text() + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptC.get(i).text() + '"'+",\n");

            String optD = movieoptD.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptD.get(i).text() + '"'+",\n");

            String answer = movieanswer.get(i).text().substring(4).trim();
            int len = answer.length();
            moviecsv.print('"' + movieanswer.get(i).text() + '"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("movie");
            if (len == 1) {
                questions.setQuestionType(1);
            } else {
                questions.setQuestionType(2);
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
            String stringQuestion = cricketqstn.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketqstn.get(i).text()+'"'+",\n");

            String optA = cricketoptA.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptA.get(i).text()+'"'+",\n");

            String optB = cricketoptB.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptB.get(i).text()+'"'+",\n");

            String optC = cricketoptC.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptC.get(i).text()+'"'+",\n");

            String optD = cricketoptD.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptD.get(i).text()+'"'+",\n");

            String answer = cricketanswer.get(i).text().substring(4).trim();
            int len = answer.length();
            cricketcsv.print('"'+cricketanswer.get(i).text()+'"'+",\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);

            questions.setQuestionText(stringQuestion);
            questions.setOptions(map);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("Cricket");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);
        }
        cricketfout.close();

        return true;
    }

    public List<Questions> findQuestions() {
        return scrapingRepository.findAll();
    }

    public void addQuestions(Questions questions){
        scrapingRepository.save(questions);
    }


    public List<Questions> findByCategory(String string){
        return scrapingRepository.findByCategoryId(string);
    }



    @Override
    public boolean getLiteratureQuestions() throws IOException {


        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/articles/literature-general-knowledge-quiz-1314427586-1").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        Elements movieanswer = moviedocument.select("strong:contains(Ans:)");
        FileOutputStream moviefout = new FileOutputStream("LiteratureQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

            //todo: move three line into one line
            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptA.get(i).text() + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptB.get(i).text() + '"'+",\n");

            String optC = movieoptC.get(i+1).text().substring(2).trim();
            moviecsv.print('"' + movieoptC.get(i+1).text() + '"'+",\n");

            String optD = movieoptD.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptD.get(i).text() + '"'+",\n");

            String answer = movieanswer.get(i).text().substring(4).trim();
            int len = answer.length();
            moviecsv.print('"' + answer + '"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
           // map.put("question", stringquestion);
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);

            questions.setQuestionText(stringquestion);
            questions.setOptions(map);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("literature");
            if (len == 1) {
                questions.setQuestionType(1);
            } else {
                questions.setQuestionType(2);
            }
            scrapingRepository.save(questions);
        }
        moviefout.close();
        return false;
    }

    @Override
    public boolean getLiteratureQuestions1() throws IOException {
        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-the-novels-and-literatures-1487598247-1").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        movieoptA.remove(5);
        movieoptA.remove(5);
        movieoptA.remove(6);
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        movieoptB.remove(5);
        movieoptB.remove(5);
        movieoptB.remove(6);
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        movieoptC.remove(0);
        movieoptC.remove(5);
        movieoptC.remove(5);
        movieoptC.remove(6);
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        movieoptD.remove(5);
        movieoptD.remove(5);
        movieoptD.remove(6);
        Elements movieanswer = moviedocument.select("strong:contains(Ans.)");
        movieanswer.remove(5);
        movieanswer.remove(5);
        movieanswer.remove(6);
        FileOutputStream moviefout = new FileOutputStream("LiteratureQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

            //todo: move three line into one line
            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptA.get(i).text() + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptB.get(i).text() + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptC.get(i).text() + '"'+",\n");

            String optD = movieoptD.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptD.get(i).text() + '"'+",\n");

            String answer = movieanswer.get(i).text().substring(4).trim();
            int len = answer.length();
            moviecsv.print('"' + answer + '"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            // map.put("question", stringquestion);
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);

            questions.setQuestionText(stringquestion);
            questions.setOptions(map);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("literature");
            if (len == 1) {
                questions.setQuestionType(1);
            } else {
                questions.setQuestionType(2);
            }
            scrapingRepository.save(questions);


        }
        moviefout.close();
        return false;
    }

    @Override
    public boolean getMovieQuestions1() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-the-indian-classical-theatre-and-drama-1549368269-1").get();
        Elements movieqstn = moviedocument.select("p:contains(?)");
        Elements movieoptA = moviedocument.select("p:matches(A. )");
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        Elements movieanswer = moviedocument.select("p:contains(Ans:)");
        FileOutputStream moviefout = new FileOutputStream("MovieQuestionBank1.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String question  = movieqstn.get(i).text().substring(3).trim();
            moviecsv.print('"' + question + '"'+",\n");

            String optA = movieoptA.get(i+1).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(i+1).text().substring(3).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(i+1).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");

            String optD = movieoptD.get(i+1).text().substring(3).trim();
            moviecsv.print('"' + optD + '"'+",\n");

            String answer = movieanswer.get(i+1).text().substring(4).trim();
            int len = answer.length();
            moviecsv.print('"' + answer + '"'+",\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setOptions(map);
            questions.setQuestionText(question);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("movie");
            if (len == 1) {
                questions.setQuestionType(1);
            } else {
                questions.setQuestionType(2);
            }
            scrapingRepository.save(questions);
        }
        moviefout.close();
        return false;
    }

    @Override
    public boolean getCricketQuestions1() throws IOException {

        Document document1 = Jsoup.connect("https://www.jagranjosh.com/articles/cricket-general-knowledge-quiz-1314792254-1?ref=list_art").get();
        Elements cricketqstn = document1.select("p:contains(?)");
        Elements cricketoptA = document1.select("p:contains(A.)");
        Elements cricketoptB = document1.select("p:contains(B.)");
        Elements cricketoptC = document1.select("p:contains(C.)");
        Elements cricketoptD = document1.select("p:matches(D. )");
        Elements cricketanswer = document1.select("p:contains(Ans:)");
        FileOutputStream cricketfout=new FileOutputStream("CricketQuestionBank1.csv");
        PrintStream cricketcsv=new PrintStream(cricketfout);

        for (int i=0;i<cricketqstn.size();i++){
            String stringQuestion = cricketqstn.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketqstn.get(i).text()+'"'+",\n");

            String optA = cricketoptA.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptA.get(i).text()+'"'+",\n");

            String optB = cricketoptB.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptB.get(i).text()+'"'+",\n");

            String optC = cricketoptC.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptC.get(i).text()+'"'+",\n");

            String optD = cricketoptD.get(i).text().substring(3).trim();
            cricketcsv.print('"'+cricketoptD.get(i).text()+'"'+",\n");

            String answer = cricketanswer.get(i).text().substring(4).trim();
            int len = answer.length();
            cricketcsv.print('"'+cricketanswer.get(i).text()+'"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);

            questions.setQuestionText(stringQuestion);
            questions.setOptions(map);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("Cricket");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        cricketfout.close();

        return false;
    }


}
