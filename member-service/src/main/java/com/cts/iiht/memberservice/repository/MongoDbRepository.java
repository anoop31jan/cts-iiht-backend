package com.cts.iiht.memberservice.repository;

import com.cts.iiht.memberservice.entity.ProjectMemberDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDbRepository extends MongoRepository<ProjectMemberDoc, String> {

    ProjectMemberDoc findByMemberId(String memberId);

}
