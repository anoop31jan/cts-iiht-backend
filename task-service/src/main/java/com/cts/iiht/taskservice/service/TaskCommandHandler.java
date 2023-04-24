package com.cts.iiht.taskservice.service;

import com.cts.iiht.taskservice.helper.*;
import com.cts.iiht.taskservice.model.*;
import org.apache.kafka.clients.admin.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.*;
import org.springframework.messaging.*;
import org.springframework.messaging.support.*;
import org.springframework.stereotype.*;

@Service
public class TaskCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskCommandHandler.class);

    @Autowired
    private TaskServiceHelper taskServiceHelper;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private NewTopic topic;

    public void sendMessage(final AssignTaskCommand assignTaskCommand) {

        TaskAssignedEvent assignedEvent = taskServiceHelper.createTaskAssignedEvent(assignTaskCommand);
        LOGGER.info("MemberAddedEvent {} ",assignedEvent);
        //create message
        Message<TaskAssignedEvent> message = MessageBuilder
                .withPayload(assignedEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .setHeader("eventName",assignedEvent.getEventName())
                .build();
        kafkaTemplate.send(message);


    }

}
