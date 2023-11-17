package com.cts.iiht.memberservice.service;

import com.cts.iiht.memberservice.entity.ProjectMember;
import com.cts.iiht.memberservice.helper.*;
import com.cts.iiht.memberservice.model.*;
import com.cts.iiht.memberservice.repository.MemberRepository;
import lombok.*;
import org.apache.kafka.clients.admin.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.*;
import org.springframework.messaging.*;
import org.springframework.messaging.support.*;
import org.springframework.stereotype.*;

import java.time.LocalDate;

@Service
public class AddMemberCommandHandler {

    private static final Logger LOGGER =LoggerFactory.getLogger(AddMemberCommandHandler.class);
    @Autowired
    private NewTopic topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    MemberRepository memberRepository;

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
        save(event);
        return event;
    }


    public void save(@NonNull final MemberAddedEvent memberAddedEvent) {

        ProjectMember projectMember = memberServiceHelper.craeteProjectMemberEntityForMySQL(memberAddedEvent);
        memberRepository.save(projectMember);
        LOGGER.info("Data saved successfully");

    }

    public ProjectMember getProjectMemberByMemberId(final String memberId){

        return  memberRepository.getProjectMemberBymemberId(memberId);

    }



    public void updateMemberAllocationpercentage(@NonNull final ProjectMember projectMember) {

        if (projectMember.getProjectEndDate().isBefore(LocalDate.now())) {
            projectMember.setAllocationPercentage(0);

        } else{
            projectMember.setAllocationPercentage(100);
        }
        memberRepository.save(projectMember);
    }


}
