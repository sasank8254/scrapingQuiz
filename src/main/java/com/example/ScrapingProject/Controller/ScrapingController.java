package com.example.ScrapingProject.Controller;

import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.service.ScrapingService;
import com.example.ScrapingProject.service.ScrapingServiceCricket;
import com.example.ScrapingProject.service.ScrapingServiceLiterature;
import com.example.ScrapingProject.service.ScrapingServiceTechnology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/scrapper")
public class ScrapingController {

    //todo : create one Scraping service interface
    //todo : create mulitple implementation classes
    //todo : autowire a collection of ScrapingService
    //todo :: in the controller method iterate and execute methiod

    @Autowired
    ScrapingService scrapingService;

    @Autowired
    ScrapingServiceCricket scrapingServiceCricket;

    @Autowired
    ScrapingServiceTechnology scrapingServiceTechnology;

    @Autowired
    ScrapingServiceLiterature scrapingServiceLiterature;


    @PostMapping("/getQuestions")
    public boolean getMovQuestions() throws IOException {
        return scrapingService.getMovieQuestions();
    }
    @PostMapping("/getCricketQuestions")
    public boolean getCricQuestions() throws IOException {
        return scrapingServiceCricket.getCricketQuestions();
    }
    @PostMapping("/getTechnolgyQuestions")
    public boolean getTechQuestions() throws IOException {
        return scrapingServiceTechnology.getTechnologyQuestions();
    }

    @PostMapping("/getLiteratureQuestions")
    public boolean getLitQuestions() throws IOException {
        return scrapingServiceLiterature.getLiteratureQuestions();
    }

    @GetMapping("/get")
    public List<Questions> findAll(){
        return scrapingService.findQuestions();
    }


}
