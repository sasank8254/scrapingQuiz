package com.example.ScrapingProject.Controller;

import com.example.ScrapingProject.entity.Questions;
import com.example.ScrapingProject.repository.ScrapingRepository;
import com.example.ScrapingProject.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/getMovieQuestions")
    public boolean getMovieQuestions() throws IOException {
        return scrapingService.getMovieQuestions1();
    }

    @PostMapping("/getCricketQuestions")
    public boolean getCricQuestions() throws IOException {
        return scrapingService.getCricketQuestions();
    }


    @PostMapping("/getCricketQuestions1")
    public boolean getCricketQuestions() throws IOException {
        return scrapingService.getCricketQuestions1();
    }

    @PostMapping("/getLiteratureQuestions")
    public boolean getLitQuestions() throws IOException {
        return scrapingService.getLiteratureQuestions();
    }

    @GetMapping("/get")
    public List<Questions> findAll(){
        return scrapingService.findQuestions();
    }

    @GetMapping("/getQuestionsByCategory/{categoryId}")
    public List<Questions> getQUestionsByCategory(@PathVariable("categoryId") String categoryId){

        return scrapingService.findByCategory(categoryId);
//        return scrapingRepository.findByCategoryId(categoryId);


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
