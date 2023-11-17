package com.cts.iiht.taskservice.repository;

import com.cts.iiht.taskservice.entity.TaskDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoDBRepository extends MongoRepository<TaskDoc, String> {

 List<TaskDoc> findByMemberId(String memberId);
}
