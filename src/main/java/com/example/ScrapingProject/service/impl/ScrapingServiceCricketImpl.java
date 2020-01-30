package com.example.ScrapingProject.service.impl;

import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.repository.ScrapingRepository;
import com.example.ScrapingProject.service.ScrapingServiceCricket;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
@Service
public class ScrapingServiceCricketImpl implements ScrapingServiceCricket {
    @Autowired
    ScrapingRepository scrapingRepository;

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

}
