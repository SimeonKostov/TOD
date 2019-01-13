package com.tod.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tod.model.Follow;

@Component
@Repository
public interface FollowRepository extends MongoRepository<Follow, String>{

	@Query(value="{ $and: [{'follower.username':?0}, {'following.username':?1}]}")
	Follow findByFollowerAndFollowing(String follower, String followed);
	
	Page<Follow> findByFollowerUsername(String username, Pageable pageable);
	
	Page<Follow> findByFollowingUsername(String username, Pageable pageable);
}
