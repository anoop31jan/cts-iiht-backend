package com.cts.iiht.memberservice.service;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.memberservice.model.*;
import org.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.messaging.*;
import org.springframework.stereotype.*;

@Service
public class AddMemberConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddMemberConsumerService.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}"
            , groupId = "@{spring.kafka.consumer.group-id}")
    public void consume(Message messageEvent) {
            final String eventName = (String) messageEvent.getHeaders().get("eventName");
        LOGGER.info("Event received at member consumer {} ",eventName);
        LOGGER.info("Event Name {} ",messageEvent.getPayload());

    }
}
