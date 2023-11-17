package com.cts.iiht.taskservice.service;

import com.cts.iiht.taskservice.entity.*;
import com.cts.iiht.taskservice.helper.*;
import com.cts.iiht.taskservice.model.*;
import com.cts.iiht.taskservice.repository.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.kafka.annotation.*;
import org.springframework.messaging.*;
import org.springframework.stereotype.*;

import static com.cts.iiht.taskservice.constant.ProjectTrackerConstant.EVENT_NAME;
import static com.cts.iiht.taskservice.constant.ProjectTrackerConstant.TASK_ASSIGNED_EVENT;


@Service
public class TaskAssignedConsumer {
    @Autowired
    MongoRepository mongoRepository;

    @Autowired
    TaskServiceHelper taskServiceHelper;

    private static Logger LOGGER = LoggerFactory.getLogger(TaskAssignedConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}"
            , groupId = "@{spring.kafka.consumer.group-id}")
    public void consume(@NonNull final Message messageEvent) {
        final String eventName = (String) messageEvent.getHeaders().get(EVENT_NAME);
        LOGGER.info("Event received at task consumer {} ",eventName);
        LOGGER.info("Event Name {} ",messageEvent.getPayload());
        if (TASK_ASSIGNED_EVENT.equalsIgnoreCase(eventName)) {
            TaskAssignedEvent taskAssignedEvent = (TaskAssignedEvent) messageEvent.getPayload();
            save(taskAssignedEvent);
        }

    }

    public void save(@NonNull final TaskAssignedEvent taskAssignedEvent) {

        TaskDoc task = taskServiceHelper.createTaskAssignedEntityForReadFlow(taskAssignedEvent);
        mongoRepository.save(task);
        LOGGER.info("Data saved successfully in Task table in mongo db");

    }

}
