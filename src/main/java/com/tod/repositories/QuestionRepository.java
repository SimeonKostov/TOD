package com.tod.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tod.model.Question;

@Component
@Repository
public interface QuestionRepository extends MongoRepository<Question, String>{

	Question findById(String id);
}
