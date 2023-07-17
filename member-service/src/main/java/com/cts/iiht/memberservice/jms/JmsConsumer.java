package com.cts.iiht.memberservice.jms;

import com.cts.iiht.memberservice.entity.ProjectMember;
import com.cts.iiht.memberservice.helper.MemberServiceHelper;
import com.cts.iiht.memberservice.model.MemberAddedEvent;
import com.cts.iiht.memberservice.repository.MembersRepository;
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
    MemberServiceHelper memberServiceHelper;

    @Autowired
    MembersRepository memberRepository;

    @JmsListener(destination = "${spring.activemq.queue.name}")
    public void receiveMessage(@NonNull final Message message){

        ByteSequence messageSequence = message.getContent();
        byte[] data = messageSequence.getData();
        MemberAddedEvent memberAddedEvent = (MemberAddedEvent) SerializationUtils.deserialize(data);
        LOGGER.info("Received messages : {} ",message);
        LOGGER.info("Received Payload : {} ",memberAddedEvent);
        LOGGER.info("event transaction id : {} ",memberAddedEvent.getTransactionId());
        final ProjectMember projectMember = memberServiceHelper.craeteProjectMemberEntity(memberAddedEvent);
        memberRepository.addMember(projectMember);
        LOGGER.info("Data saved successfully");

    }

}
