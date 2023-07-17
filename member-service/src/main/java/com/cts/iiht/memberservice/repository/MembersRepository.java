package com.cts.iiht.memberservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.cts.iiht.memberservice.entity.ProjectMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MembersRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public ProjectMember addMember(ProjectMember projectMember) {

        dynamoDBMapper.save(projectMember);
        return projectMember;
    }

    public ProjectMember findByMemberId(final String memberId) {

        return dynamoDBMapper.load(ProjectMember.class, memberId);
    }

    public String updateProjectMember(ProjectMember projectMember) {

        dynamoDBMapper.save(projectMember, buildSaveExpression(projectMember));
        return "record updated";
    }

    private DynamoDBSaveExpression buildSaveExpression(ProjectMember projectMember) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("memberId", new ExpectedAttributeValue(new AttributeValue().withS(String.valueOf(projectMember.getMemberId()))));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;

    }

    public List<ProjectMember> findAllMember() {

        return dynamoDBMapper.scan(ProjectMember.class, new DynamoDBScanExpression());
    }

}
