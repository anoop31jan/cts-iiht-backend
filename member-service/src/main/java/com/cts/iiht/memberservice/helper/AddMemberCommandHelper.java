package com.cts.iiht.memberservice.helper;

import com.cts.iiht.memberservice.model.*;
import org.springframework.stereotype.*;

import java.time.*;

import static com.cts.iiht.basedomain.constant.ProjectTrackerConstant.MEMBER_CREATED_EVENT;

@Component
public class AddMemberCommandHelper {

    public MemberAddedEvent createMemberAddedEvent(AddMemberCommand addMemberCommand) {

        return MemberAddedEvent.builder()
                .eventName(MEMBER_CREATED_EVENT)
                .createdAt(LocalDateTime.now().toString())
                .memberId(addMemberCommand.getMemberId())
                .memberName(addMemberCommand.getMemberName())
                .allocationPercentage(addMemberCommand.getAllocationPercentage())
                .profileDescription(addMemberCommand.getProfileDescription())
                .projectEndDate(addMemberCommand.getProjectEndDate())
                .projectStartDate(addMemberCommand.getProjectStartDate())
                .yearsOfExperienc(addMemberCommand.getYearsOfExperienc())
                .skillset(addMemberCommand.getSkillset())
                .build();


    }
}

