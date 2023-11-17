package com.cts.iiht.memberservice.service;

import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.helper.*;
import com.cts.iiht.memberservice.model.*;
import com.cts.iiht.memberservice.repository.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.messaging.*;
import org.springframework.stereotype.*;

import static com.cts.iiht.memberservice.constant.ProjectTrackerConstant.MEMBER_CREATED_EVENT;

@Service
public class AddMemberConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddMemberConsumerService.class);

    @Autowired
    MongoDbRepository mongoDbRepository;
    @Autowired
    MemberServiceHelper memberServiceHelper;



    @KafkaListener(topics = "${spring.kafka.topic.name}"
            , groupId = "@{spring.kafka.consumer.group-id}")
    public void consume(@NonNull final Message<?> messageEvent) {
            final String eventName = (String) messageEvent.getHeaders().get("eventName");
        LOGGER.info("Event received at member consumer {} ",eventName);
        LOGGER.info("Event Name {} ",messageEvent.getPayload());
        if (MEMBER_CREATED_EVENT.equalsIgnoreCase(eventName)) {
            MemberAddedEvent memberAddedEvent = (MemberAddedEvent) messageEvent.getPayload();
            saveToMongoDb(memberAddedEvent);
        }

    }

    public void saveToMongoDb(@NonNull final MemberAddedEvent memberAddedEvent) {

        ProjectMemberDoc projectMember = memberServiceHelper.craeteProjectMemberEntity(memberAddedEvent);
        mongoDbRepository.save(projectMember);
        LOGGER.info("Data saved successfully to mongo db");

    }
}
