package com.example.ScrapingProject.service.impl;

import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.repository.ScrapingRepository;
import com.example.ScrapingProject.service.ScrapingServiceLiterature;
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
public class ScrapingServiceLiteratureImpl implements ScrapingServiceLiterature {
    @Autowired
    ScrapingRepository scrapingRepository;

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
}
