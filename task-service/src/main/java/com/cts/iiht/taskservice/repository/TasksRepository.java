package com.cts.iiht.taskservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.cts.iiht.taskservice.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TasksRepository {

 @Autowired
 DynamoDBMapper dynamoDBMapper;

    private static Logger LOGGER = LoggerFactory.getLogger(TasksRepository.class);


public void saveTask(Task task) {

    dynamoDBMapper.save(task);
    LOGGER.info(" Task Deatils saved successfully");
}

public List<Task> getTaskDetailsByMemberId(final String memberId){

    List<Task> taskList = dynamoDBMapper.scan(Task.class, new DynamoDBScanExpression());

    return taskList.stream()
            .filter(task -> memberId.equalsIgnoreCase(task.getMemberId()))
            .collect(Collectors.toList());
}

}
