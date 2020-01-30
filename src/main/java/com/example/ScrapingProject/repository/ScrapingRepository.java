package com.example.ScrapingProject.repository;

import com.example.ScrapingProject.entity.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapingRepository extends MongoRepository<Questions,String> {
}
