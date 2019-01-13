package com.tod.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tod.model.ChallengeVideo;

@Component
@Repository
public interface ChallengeVideoRepository extends MongoRepository<ChallengeVideo, String>{
	
	ChallengeVideo findById(String id);

	@Query(value="{user.username:?0}")
	Page<ChallengeVideo> findByUsername(String username, Pageable pageable);
}
