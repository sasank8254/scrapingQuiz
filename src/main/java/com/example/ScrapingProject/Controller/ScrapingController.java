package com.example.ScrapingProject.Controller;

import com.example.ScrapingProject.dto.QuestionsDTO;
import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.repository.ScrapingRepository;
import com.example.ScrapingProject.service.ScrapingService;
import com.example.ScrapingProject.service.ScrapingServiceCricket;
import com.example.ScrapingProject.service.ScrapingServiceLiterature;
import com.example.ScrapingProject.service.ScrapingServiceTechnology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/scrapper")
@CrossOrigin(origins = "*" , allowedHeaders = "*" )
public class ScrapingController {

    //todo : create one Scraping service interface
    //todo : create mulitple implementation classes
    //todo : autowire a collection of ScrapingService
    //todo :: in the controller method iterate and execute methiod

    @Autowired
    ScrapingService scrapingService;


    ///TESTING
    @Autowired
    ScrapingRepository scrapingRepository;


    @PostMapping("/getQuestions")
    public boolean getMovQuestions() throws IOException {
        return scrapingService.getMovieQuestions();
    }
    @PostMapping("/getCricketQuestions")
    public boolean getCricQuestions() throws IOException {
        return scrapingService.getCricketQuestions();
    }
//    @PostMapping("/getTechnolgyQuestions")
//    public boolean getTechQuestions() throws IOException {
//        return scrapingServiceTechnology.getTechnologyQuestions();
//    }

    @PostMapping("/getLiteratureQuestions")
    public boolean getLitQuestions() throws IOException {
        return scrapingService.getLiteratureQuestions();
    }

    @GetMapping("/get")
    public List<Questions> findAll(){
        return scrapingService.findQuestions();
    }


    @PostMapping("/addQuestion")
    public void addQuestion(@RequestBody Questions questions){
        scrapingService.addQuestions(questions);
    }


    ////TESTING
    @PostMapping("/addQuestions")
    public void addQuestions(@RequestBody Questions questions){
        scrapingRepository.save(questions);
    }


}
