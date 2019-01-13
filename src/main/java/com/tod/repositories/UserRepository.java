package com.tod.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tod.model.User;

@Component
@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	User findById(String id);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByEmailAndPassword(String email, String password);
	
	Page<User> findByUsernameLike(String username, Pageable pageable);
	
	Page<User> findByFullNameLike(String username, Pageable pageable);
	
	Page<User> findByUsernameLikeOrFullNameLikeIgnoreCase(String username, String fullName, Pageable pageable);
}
