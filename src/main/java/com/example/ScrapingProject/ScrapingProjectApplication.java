package com.example.ScrapingProject;

import com.example.ScrapingProject.repository.ScrapingRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;



@SpringBootApplication
public class ScrapingProjectApplication {
    public static void main(String[] args) {

        SpringApplication.run(ScrapingProjectApplication.class, args);
    }

}
