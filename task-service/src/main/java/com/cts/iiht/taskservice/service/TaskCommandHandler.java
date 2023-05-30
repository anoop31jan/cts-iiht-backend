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

import static com.cts.iiht.basedomain.constant.ProjectTrackerConstant.EVENT_NAME;

@Service
public class TaskCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskCommandHandler.class);

    @Autowired
    private TaskServiceHelper taskServiceHelper;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

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
        return assignedEvent;


    }

}
