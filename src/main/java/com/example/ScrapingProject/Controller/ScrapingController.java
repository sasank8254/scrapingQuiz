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


    @Autowired
    ScrapingService scrapingService;




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


    @PostMapping("/getFootballQuestions")
    public boolean getFootQuestions() throws IOException {
        return scrapingService.getFootballQuestions();
    }


    @PostMapping("/getSportsQuestions")
    public boolean getSportsQuestions() throws IOException {
        return scrapingService.getSportsQuestions();
    }

    @PostMapping("/getSportsQuestions1")
    public boolean getSportsQuestions1() throws IOException {
        return scrapingService.getSportsQuestions1();
    }


    @PostMapping("/getTennisQuestions")
    public boolean getTennisQuestions() throws IOException {
        return scrapingService.getTennisQuestions();
    }


    @PostMapping("/getTennisQuestions1")
    public boolean getTennisQuestions1() throws IOException {
        return scrapingService.getTennisQuestions1();
    }


    @PostMapping("/getHockeyQuestions")
    public boolean getHockeyQuestions() throws IOException {
        return scrapingService.getHockeyQuestions();
    }


    @PostMapping("/getCricketQuestions1")
    public boolean getCricketQuestions() throws IOException {
        return scrapingService.getCricketQuestions1();
    }

    @PostMapping("/getLiteratureQuestions")
    public boolean getLitQuestions() throws IOException {
        return scrapingService.getLiteratureQuestions();
    }

    @PostMapping("/getLiteratureQuestions1")
    public boolean getLiteratureQuestions() throws IOException {
        return scrapingService.getLiteratureQuestions1();
    }


    @PostMapping("/getBollywoodQuestions1")
    public boolean getBollywoodQuestions() throws IOException {
        return scrapingService.getBollywoodQuestions();
    }


    @PostMapping("/getTennisQuestions2")
    public boolean getTennisQuestions2() throws IOException {
        return scrapingService.getTennisQuestions2();
    }

    @PostMapping("/getTennisQuestions3")
    public boolean getTennisQuestions3() throws IOException {
        return scrapingService.getTennisQuestions3();
    }

    @PostMapping("/getCricketQuestions2")
    public boolean getCricketQuestions2() throws IOException {
        return scrapingService.getCricketQuestions2();
    }


    @PostMapping("/getCricketQuestions3")
    public boolean getCricketQuestions3() throws IOException {
        return scrapingService.getCricketQuestions3();
    }

    @PostMapping("/getHollywoodQuestions1")
    public boolean getHollywoodQuestions1() throws IOException {
        return scrapingService.getHollywoodQuestions();
    }

    @PostMapping("/getHollywoodQuestions2")
    public boolean getHollywoodQuestions2() throws IOException {
        return scrapingService.getHollywoodQuestions1();
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


    @PostMapping("/addQuestions")
    public void addQuestion(@RequestBody Questions questions){
        scrapingService.addQuestions(questions);
    }


    ////TESTING
//    @PostMapping("/addQuestions")
//    public void addQuestions(@RequestBody Questions questions){
//        scrapingRepository.save(questions);
//    }


}
