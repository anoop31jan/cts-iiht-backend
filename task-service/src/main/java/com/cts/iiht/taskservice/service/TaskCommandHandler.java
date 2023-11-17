package com.cts.iiht.taskservice.service;

import com.cts.iiht.taskservice.entity.Task;
import com.cts.iiht.taskservice.helper.*;
import com.cts.iiht.taskservice.model.*;
import com.cts.iiht.taskservice.repository.TaskRepository;
import lombok.NonNull;
import org.apache.kafka.clients.admin.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.*;
import org.springframework.messaging.*;
import org.springframework.messaging.support.*;
import org.springframework.stereotype.*;

import static com.cts.iiht.taskservice.constant.ProjectTrackerConstant.EVENT_NAME;


@Service
public class TaskCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskCommandHandler.class);

    @Autowired
    private TaskServiceHelper taskServiceHelper;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private NewTopic topic;

    public TaskAssignedEvent sendMessage(final AssignTaskCommand assignTaskCommand) {

        TaskAssignedEvent assignedEvent = taskServiceHelper.createTaskAssignedEvent(assignTaskCommand);
        LOGGER.info("taskAssigned Event {} ",assignedEvent);
        //create message
        Message<TaskAssignedEvent> message = MessageBuilder
                .withPayload(assignedEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .setHeader(EVENT_NAME,assignedEvent.getEventName())
                .build();
        kafkaTemplate.send(message);
        save(assignedEvent);
        return assignedEvent;

    }


    public void save(@NonNull final TaskAssignedEvent taskAssignedEvent) {

        Task task = taskServiceHelper.createTaskAssignedEntity(taskAssignedEvent);
        taskRepository.save(task);
        LOGGER.info("Data saved successfully in Task table in command flow");

    }

}
