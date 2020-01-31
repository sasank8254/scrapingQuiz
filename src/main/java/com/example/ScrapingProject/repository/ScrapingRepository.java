package com.example.ScrapingProject.repository;

import com.example.ScrapingProject.entity.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapingRepository extends MongoRepository<Questions,String> {
    List<Questions> findByCategoryId(String categoryId);
}
