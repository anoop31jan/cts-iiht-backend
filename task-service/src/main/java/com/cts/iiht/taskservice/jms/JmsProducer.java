package com.cts.iiht.taskservice.jms;

import com.cts.iiht.taskservice.helper.TaskServiceHelper;
import com.cts.iiht.taskservice.model.AssignTaskCommand;
import com.cts.iiht.taskservice.model.TaskAssignedEvent;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;

@Component
public class JmsProducer {

    private final JmsTemplate jmsTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private Queue queue;

    @Autowired
    private TaskServiceHelper taskServiceHelper;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public TaskAssignedEvent processTaskAssignedCommand(@NonNull AssignTaskCommand assignTaskCommand) {
        TaskAssignedEvent assignedEvent = taskServiceHelper.createTaskAssignedEvent(assignTaskCommand);

        LOGGER.info("assignedEvent {} ", assignedEvent);
        String message = "";

        try {
            sendMesage(assignedEvent);
        } catch (Exception ex) {
            LOGGER.error("json processing error occurred {} stack trace {} ", ex.getMessage(), ex.getStackTrace());
        }
        return assignedEvent;
    }

    public void sendMesage(TaskAssignedEvent message) throws JMSException {

        LOGGER.info("queue name: {} message : {} ", queue.getQueueName(), message);

        jmsTemplate.convertAndSend(queue.getQueueName(), message);
    }


}
