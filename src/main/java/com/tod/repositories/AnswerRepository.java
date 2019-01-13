package com.tod.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tod.model.Answer;

@Component
@Repository
public interface AnswerRepository extends MongoRepository<Answer, String>{
	
	Answer findById(String id);
	
	@Query(value="{user.username:?0}")
	Page<Answer> findByUsername(String username, Pageable pageable);
	
	@Query(value="{question.id:?0}")
	Answer findByQuestionId(String id);
	
	
	
}
