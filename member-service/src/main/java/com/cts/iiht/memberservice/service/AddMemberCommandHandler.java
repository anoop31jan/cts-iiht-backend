/*package com.cts.iiht.memberservice.service;

import com.cts.iiht.memberservice.helper.*;
import com.cts.iiht.memberservice.model.*;
import lombok.*;
import org.apache.kafka.clients.admin.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.*;
import org.springframework.messaging.*;
import org.springframework.messaging.support.*;
import org.springframework.stereotype.*;

@Service
public class AddMemberCommandHandler {

    private static final Logger LOGGER =LoggerFactory.getLogger(AddMemberCommandHandler.class);
    @Autowired
    private NewTopic topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private MemberServiceHelper memberServiceHelper;

    public MemberAddedEvent sendMessage(@NonNull final AddMemberCommand addMemberCommand) {
        MemberAddedEvent event =memberServiceHelper.createMemberAddedEvent(addMemberCommand);

        LOGGER.info("MemberAddedEvent {} ",event);
        //create message
        Message<MemberAddedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .setHeader("eventName",event.getEventName())
                .build();
        kafkaTemplate.send(message);
        return event;
    }



}
*/