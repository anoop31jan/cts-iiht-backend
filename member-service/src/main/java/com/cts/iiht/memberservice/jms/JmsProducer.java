package com.cts.iiht.memberservice.jms;

import com.cts.iiht.memberservice.helper.MemberServiceHelper;
import com.cts.iiht.memberservice.model.AddMemberCommand;
import com.cts.iiht.memberservice.model.MemberAddedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JmsProducer {

    private final JmsTemplate jmsTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private Queue queue;

    @Autowired
    private MemberServiceHelper memberServiceHelper;

    public JmsProducer(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    public MemberAddedEvent processAddMemberCommand(@NonNull AddMemberCommand addMemberCommand){
        MemberAddedEvent event =memberServiceHelper.createMemberAddedEvent(addMemberCommand);

        LOGGER.info("MemberAddedEvent {} ",event);
        String message ="";
        //create message
        //ObjectMapper objectMapper = new ObjectMapper();
        try {
            //message = objectMapper.writeValueAsString(event);
            sendMesage(event);
        }catch (Exception ex){
            LOGGER.error("json processing error occurred {} stack trace {} ",ex.getMessage(),ex.getStackTrace());
        }
        return event;
    }

    public void sendMesage(MemberAddedEvent message) throws JMSException {

        LOGGER.info("queue name: {} message : {} ",queue.getQueueName(),message);

        jmsTemplate.convertAndSend(queue.getQueueName(), message);
    }


}
