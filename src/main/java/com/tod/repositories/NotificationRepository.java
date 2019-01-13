package com.tod.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;

import com.tod.model.Notification;

@Component
@Repository
public interface NotificationRepository extends MongoRepository<Notification, String>{
	
	Notification findById(String id);
	
	@Query(value="{'receiver.username':?0}")
	Page<Notification> findByReceiver(String receiver, Pageable pageable);
	
	@Query(value="{ $and: [ {'checked':false}, { 'receiver.username':?0 }] }")
	List<Notification> findByCheckedIsFalseAndReceiver(String username);
}
