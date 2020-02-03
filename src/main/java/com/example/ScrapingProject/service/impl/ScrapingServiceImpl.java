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
            questions.setCategoryId("cricket");
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
            questions.setCategoryId("cricket");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        cricketfout.close();

        return false;
    }

    @Override
    public boolean getCricketQuestions2() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.welovequizzes.com/cricket-quiz-questions-and-answers/").get();

        Elements movieqstn = moviedocument.select("h3:contains(.");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        System.out.println(movieoptA.text());
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:matchesown(B. )");
        System.out.println(movieoptB.text());
        System.out.println(movieoptB.size());
        Elements movieoptC = moviedocument.select("p:matchesown(C. )");
        System.out.println(movieoptC.text());
        System.out.println(movieoptC.size());


        Elements movieanswer = moviedocument.select("em:contains(.)");
        System.out.println(movieanswer.text());
        System.out.println(movieanswer.size());
        FileOutputStream moviefout = new FileOutputStream("cricketQuestionBank2.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size() ; i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3).trim();
            moviecsv.print('"' + stringquestion + '"'+",\n");

            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");



            String answer = movieanswer.get(i).text().trim().substring(0,1);
            moviecsv.print('"' + answer + '"'+",\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("cricket");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();


        return false;
    }

    @Override
    public boolean getCricketQuestions3() throws IOException {

        Document moviedocument = Jsoup.connect("http://hardevgk.blogspot.com/2019/01/cricket-quiz-questions-answers.html").get();

        Elements movieqstn = moviedocument.select("b:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("span:contains(A.)");
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("span:contains(B.)");
        Elements movieoptC = moviedocument.select("span:contains(C.)");
        Elements movieoptD = moviedocument.select("span:contains(D.)");
        System.out.println(movieoptD.text());
        System.out.println(movieoptD.size());


        Elements movieanswer = moviedocument.select("b:contains(Correct Answer)");
        System.out.println(movieanswer.text());
        System.out.println(movieanswer.size());
        FileOutputStream moviefout = new FileOutputStream("iccQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0,j=0; i < movieqstn.size()&&j<movieoptA.size()  ; i++,j+=2) {
            String stringquestion = movieqstn.get(i).text();
            int len = stringquestion.length();
            stringquestion = stringquestion.substring(3).trim();
            moviecsv.print('"' + stringquestion + '"'+",\n");

            String optA = movieoptA.get(j).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(j).text().substring(2).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(j).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");

            String optD = movieoptD.get(j).text().substring(2).trim();
            moviecsv.print('"' + optD + '"'+",\n");


            String answer = movieanswer.get(i).text().substring(17).trim();
            moviecsv.print('"' + answer + '"'+",\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);

            questions.setQuestionText(stringquestion);
            questions.setOptions(map);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("cricket");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }

    @Override
    public boolean getFootballQuestions() throws IOException {

        Document footballdocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/quiz-on-sports-football-set-1-1446725050-1").get();
        Elements footballqstn = footballdocument.select("p:contains(?)");
        Elements footballoptA = footballdocument.select("p:contains(A. )");
        Elements footballoptB = footballdocument.select("p:contains(B.)");
        Elements footballoptC = footballdocument.select("p:contains(C.)");
        Elements footballoptD = footballdocument.select("p:contains(D.)");
        Elements footballanswer = footballdocument.select("p:contains(Ans:)");
        FileOutputStream footballfout = new FileOutputStream("FootballQuestionBank1.csv");
        PrintStream footballcsv = new PrintStream(footballfout);
        for (int i = 0; i < 4; i++) {
            String question  = footballqstn.get(i).text().substring(3).trim();
            footballcsv.print('"' + footballqstn.get(i).text() + '"'+",\n");
            String optA = footballoptA.get(i).text().substring(2).trim();
            footballcsv.print('"' + optA + '"'+",\n");
            String optB = footballoptB.get(i).text().substring(2).trim();
            footballcsv.print('"' + optB + '"'+",\n");
            String optC = footballoptC.get(i).text().substring(2).trim();
            footballcsv.print('"' + optC + '"'+",\n");
            String optD = footballoptD.get(i).text().substring(2).trim();
            footballcsv.print('"' + optD + '"'+",\n");
            String answer = footballanswer.get(i).text().substring(4).trim();
            footballcsv.print('"' + answer + '"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);

            questions.setQuestionText(question);
            questions.setOptions(map);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("football");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        for (int i=4;i<footballqstn.size();i++){
            String question  = footballqstn.get(i).text().substring(3).trim();
            footballcsv.print('"' + footballqstn.get(i).text() + '"'+",\n");
            String optA = footballoptA.get(i+1).text().substring(2).trim();
            footballcsv.print('"' + optA + '"'+",\n");
            String optB = footballoptB.get(i+1).text().substring(2).trim();
            footballcsv.print('"' + optB + '"'+",\n");
            String optC = footballoptC.get(i+1).text().substring(2).trim();
            footballcsv.print('"' + optC + '"'+",\n");
            String optD = footballoptD.get(i+1).text().substring(2).trim();
            footballcsv.print('"' + optD + '"'+",\n");
            String answer = footballanswer.get(i+1).text().substring(4).trim();
            footballcsv.print('"' + answer + '"'+",\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);

            questions.setQuestionText(question);
            questions.setOptions(map);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("Football");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        footballfout.close();

        return false;
    }

    @Override
    public boolean getSportsQuestions() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-quiz-on-olympic-games-1457081177-1").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        movieoptA.remove(0);
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        movieoptB.remove(0);
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        movieoptC.remove(0);
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        movieoptD.remove(0);
        Elements movieanswer = moviedocument.select("strong:contains(Ans:)");
        movieanswer.remove(0);
        System.out.println(movieanswer.size());
        FileOutputStream moviefout = new FileOutputStream("LiteratureQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

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
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("olympics");
            if (len == 1) {
                questions.setQuestionType(1);
            } else {
                questions.setQuestionType(2);
            }
            scrapingRepository.save(questions);


        }


        return false;
    }

    @Override
    public boolean getSportsQuestions1() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-questions-and-answers-on-asian-games-1534939274-1?ref=list_gk").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        Elements movieoptA = moviedocument.select("p:contains((a))");
        Elements movieoptB = moviedocument.select("p:contains((b))");
        Elements movieoptC = moviedocument.select("p:contains((c))");
        Elements movieoptD = moviedocument.select("p:contains((d))");
        Elements movieanswer = moviedocument.select("strong:contains(Answer)");
        System.out.println(movieanswer.size());
        System.out.println(movieanswer);
        FileOutputStream moviefout = new FileOutputStream("SportsQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptA.get(i).text() + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptB.get(i).text() + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptC.get(i).text() + '"'+",\n");

            String optD = movieoptD.get(i).text().substring(2).trim();
            moviecsv.print('"' + movieoptD.get(i).text() + '"'+",\n");

            String answer = movieanswer.get(i).text().substring(6).trim();
            int len = answer.length();
            moviecsv.print('"' + answer + '"'+",\n");


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
            questions.setCategoryId("asiangames");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }

    @Override
    public boolean getTennisQuestions() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-quiz-on-sports-lawn-tennis-set-i-1452227151-1").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        movieoptA.remove(6);
        movieoptA.remove(6);
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        movieoptB.remove(6);
        movieoptB.remove(6);
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        movieoptC.remove(6);
        movieoptC.remove(6);
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        movieoptD.remove(6);
        movieoptD.remove(6);
        Elements movieanswer = moviedocument.select("strong:contains(Ans:)");
        System.out.println(movieanswer.size());
        movieanswer.remove(6);
        movieanswer.remove(6);
        System.out.println(movieanswer);
        FileOutputStream moviefout = new FileOutputStream("SportsQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

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
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("tennis");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();


        return false;
    }

    @Override
    public boolean getTennisQuestions1() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-quiz-on-sports-lawn-tennis-set-ii-1452579518-1").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        System.out.println(movieoptA.size());
        System.out.println(movieoptA.get(4).text("A. Roger Federer"));
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        Elements movieanswer = moviedocument.select("strong:contains(Ans:)");
        System.out.println(movieanswer.size());
        FileOutputStream moviefout = new FileOutputStream("TennisQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

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
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("tennis");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }

    @Override
    public boolean getTennisQuestions2() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.wtatennis.com/news/1536538/20-questions-test-your-knowledge-on-the-2019-wta-year-end-quiz").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("div:matchesown(A. )");
        System.out.println(movieoptA.text());
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("div:matchesown(B. )");
        System.out.println(movieoptB.text());
        System.out.println(movieoptB.size());
        Elements movieoptC = moviedocument.select("div:matchesown(C. )");
        System.out.println(movieoptC.text());
        System.out.println(movieoptC.size());



        Elements movieanswer = moviedocument.select("p:contains(Answers)");
        System.out.println(movieanswer.text());
        String[] answers = movieanswer.text().split(" ");
        System.out.println(answers[6]);
        System.out.println(movieanswer.size());
        FileOutputStream moviefout = new FileOutputStream("tennisQuestionBank1.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0,j=4; i < movieqstn.size()&& j<answers.length ; i++,j+=2) {
            String stringquestion = movieqstn.get(i).text();
            int len = stringquestion.length();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + stringquestion + '"'+",\n");

            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");



            moviecsv.print('"' + answers[j] + '"'+",\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answers[j]);
            questions.setCategoryId("tennis");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }

    @Override
    public boolean getTennisQuestions3() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.welovequizzes.com/tennis-quiz-questions-and-answers/").get();

        Elements movieqstn = moviedocument.select("h3:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        System.out.println(movieoptA.text());
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:matchesown(B. )");
        System.out.println(movieoptB.text());
        System.out.println(movieoptB.size());
        Elements movieoptC = moviedocument.select("p:matchesown(C. )");
        System.out.println(movieoptC.text());
        System.out.println(movieoptC.size());


        Elements movieanswer = moviedocument.select("div:matchesown(Answer:)");
        System.out.println(movieanswer.text());
        System.out.println(movieanswer.size());
        String[] answers = movieanswer.text().split(":");
        System.out.println(answers.length);
        FileOutputStream moviefout = new FileOutputStream("tennisQuestionBank2.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0,j=1; i < movieqstn.size()&&j<answers.length ; i++,j++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3).trim();
            moviecsv.print('"' + stringquestion + '"'+",\n");

            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");



            String[] answer = answers[j].split(" ") ;
            moviecsv.print('"' + answer[1].substring(0,1) + '"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer[1].substring(0,1));
            questions.setCategoryId("tennis");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }

    @Override
    public boolean getHockeyQuestions() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.jagranjosh.com/general-knowledge/gk-quiz-on-sports-hockey-1447850179-1?ref=list_gk").get();

        Elements movieqstn = moviedocument.select("strong:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:contains(B.)");
        Elements movieoptC = moviedocument.select("p:contains(C.)");
        Elements movieoptD = moviedocument.select("p:contains(D.)");
        Elements movieanswer = moviedocument.select("strong:contains(Ans:)");
        System.out.println(movieanswer.size());
        FileOutputStream moviefout = new FileOutputStream("HockeyQuestionBank.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0; i < movieqstn.size(); i++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

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
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);
            map.put("optionD", optD);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer);
            questions.setCategoryId("hockey");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }

    @Override
    public boolean getBollywoodQuestions() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.welovequizzes.com/bollywood-movie-quiz-questions-and-answers/").get();

        Elements movieqstn = moviedocument.select("h3:contains(?)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A.)");
        movieoptA.remove(0);
        System.out.println(movieoptA.text());
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:matchesown(B. )");
        movieoptB.remove(0);
        System.out.println(movieoptB.text());
        System.out.println(movieoptB.size());
        Elements movieoptC = moviedocument.select("p:matchesown(C. )");
        System.out.println(movieoptC.text());
        System.out.println(movieoptC.size());


        Elements movieanswer = moviedocument.select("div:matchesown(Answer:)");
        System.out.println(movieanswer.text());
        System.out.println(movieanswer.size());
        String[] answers = movieanswer.text().split(":");
        System.out.println(answers.length);
        FileOutputStream moviefout = new FileOutputStream("bollywoodQuestionBank2.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0,j=1; i < movieqstn.size()&&j<answers.length ; i++,j++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3);
            moviecsv.print('"' + stringquestion + '"'+",\n");

            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");



            String[] answer = answers[j].split(" ") ;
            moviecsv.print('"' + answer[1].substring(0,1) + '"'+",\n");


            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer[1].substring(0,1));
            questions.setCategoryId("bollywood");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();


        return false;
    }

    @Override
    public boolean getHollywoodQuestions() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.welovequizzes.com/star-wars-quiz-questions-and-answers/").get();

        Elements movieqstn = moviedocument.select("h3:contains(.)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A. )");
        System.out.println(movieoptA.text());
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:matchesown(B. )");
        System.out.println(movieoptB.text());
        System.out.println(movieoptB.size());
        Elements movieoptC = moviedocument.select("p:matchesown(C. )");
        System.out.println(movieoptC.text());
        System.out.println(movieoptC.size());


        Elements movieanswer = moviedocument.select("div:matchesown(Answer:)");
        System.out.println(movieanswer.text());
        System.out.println(movieanswer.size());
        String[] answers = movieanswer.text().split(":");
        System.out.println(answers.length);
        FileOutputStream moviefout = new FileOutputStream("hollywoodQuestionBank1.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0,j=1; i < movieqstn.size()&&j<answers.length ; i++,j++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3).trim();
            moviecsv.print('"' + movieqstn.get(i).text() + '"'+",\n");

            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");



            String[] answer = answers[j].split(" ") ;
            moviecsv.print('"' + answer[1].substring(0,1) + '"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer[1].substring(0,1));
            questions.setCategoryId("hollywood");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }

    @Override
    public boolean getHollywoodQuestions1() throws IOException {

        Document moviedocument = Jsoup.connect("https://www.welovequizzes.com/oscars-quiz-questions-and-answers/").get();

        Elements movieqstn = moviedocument.select("h3:contains(.)");
        System.out.println(movieqstn.size());
        Elements movieoptA = moviedocument.select("p:contains(A. )");
        System.out.println(movieoptA.text());
        System.out.println(movieoptA.size());
        Elements movieoptB = moviedocument.select("p:matchesown(B. )");
        System.out.println(movieoptB.text());
        System.out.println(movieoptB.size());
        Elements movieoptC = moviedocument.select("p:matchesown(C. )");
        System.out.println(movieoptC.text());
        System.out.println(movieoptC.size());


        Elements movieanswer = moviedocument.select("div:matchesown(Answer:)");
        System.out.println(movieanswer.text());
        System.out.println(movieanswer.size());
        String[] answers = movieanswer.text().split(":");
        System.out.println(answers.length);
        FileOutputStream moviefout = new FileOutputStream("oscarQuestionBank1.csv");
        PrintStream moviecsv = new PrintStream(moviefout);
        for (int i = 0,j=1; i < movieqstn.size()&&j<answers.length ; i++,j++) {
            String stringquestion = movieqstn.get(i).text();
            stringquestion = stringquestion.substring(3).trim();
            moviecsv.print('"' + stringquestion + '"'+",\n");

            String optA = movieoptA.get(i).text().substring(2).trim();
            moviecsv.print('"' + optA + '"'+",\n");

            String optB = movieoptB.get(i).text().substring(2).trim();
            moviecsv.print('"' + optB + '"'+",\n");

            String optC = movieoptC.get(i).text().substring(2).trim();
            moviecsv.print('"' + optC + '"'+",\n");



            String[] answer = answers[j].split(" ") ;
            moviecsv.print('"' + answer[1].substring(0,1) + '"'+",\n");

            Questions questions = new Questions();


            Map<String, String> map = new HashMap<String, String>();
            map.put("optionA", optA);
            map.put("optionB", optB);
            map.put("optionC", optC);


            questions.setOptions(map);
            questions.setQuestionText(stringquestion);
            questions.setQuestionFormat("text");
            questions.setAnswers(answer[1].substring(0,1));
            questions.setCategoryId("hollywood");
            questions.setQuestionType(1);
            scrapingRepository.save(questions);


        }
        moviefout.close();

        return false;
    }


}
