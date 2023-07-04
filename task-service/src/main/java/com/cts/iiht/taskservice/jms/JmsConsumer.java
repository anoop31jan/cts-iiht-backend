package com.cts.iiht.taskservice.jms;

import com.cts.iiht.taskservice.entity.Task;
import com.cts.iiht.taskservice.helper.TaskServiceHelper;
import com.cts.iiht.taskservice.model.TaskAssignedEvent;
import com.cts.iiht.taskservice.repository.TaskRepository;
import lombok.NonNull;
import org.apache.activemq.command.Message;
import org.apache.activemq.util.ByteSequence;
import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsConsumer.class);

    @Autowired
    TaskServiceHelper taskServiceHelper;

    @Autowired
    TaskRepository taskRepository;

    @JmsListener(destination = "${spring.activemq.queue.name}")
    public void receiveMessage(@NonNull final Message message){

        ByteSequence messageSequence = message.getContent();
        byte[] data = messageSequence.getData();
        TaskAssignedEvent taskAssignedEvent = (TaskAssignedEvent) SerializationUtils.deserialize(data);
        LOGGER.info("Received messages : {} ",message);
        LOGGER.info("Received Payload : {} ",taskAssignedEvent);
        LOGGER.info("event transaction id : {} ",taskAssignedEvent.getTransactionId());
        Task task = taskServiceHelper.createTaskAssignedEntity(taskAssignedEvent);
        taskRepository.save(task);
        LOGGER.info("Data saved successfully in Task table");
        LOGGER.info("Data saved successfully");

    }

}
