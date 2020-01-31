package com.example.ScrapingProject.Controller;

import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.service.ScrapingService;
import com.example.ScrapingProject.service.ScrapingServiceCricket;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @PostMapping("/getTitle")
//    public String getName() throws IOException {
//        return scrapingService.getTitle();
//    }

//    public void getQuestions() throws IOException {
//        scrapingService.getMovieQuestions();
//    }
    @PostMapping("/getQuestions")
    public boolean getMovQuestions() throws IOException {
        return scrapingService.getMovieQuestions();
    }
    @PostMapping("/getCricketQuestions")
    public boolean getCricQuestions() throws IOException {
        return scrapingServiceCricket.getCricketQuestions();
    }

    @GetMapping("/get")
    public List<Questions> findAll(){
        return scrapingService.findQuestions();
    }


}
