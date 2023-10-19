package com.cts.iiht.memberservice.helper;

import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.model.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

import static com.cts.iiht.memberservice.constant.ProjectTrackerConstant.MEMBER_CREATED_EVENT;

@Component
public class MemberServiceHelper {

    public MemberAddedEvent createMemberAddedEvent(@NonNull final AddMemberCommand addMemberCommand) {

        return MemberAddedEvent.builder()
                .eventName(MEMBER_CREATED_EVENT)
                .transactionId(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now().toString())
                .memberId(addMemberCommand.getMemberId())
                .memberName(addMemberCommand.getMemberName())
                .allocationPercentage(addMemberCommand.getAllocationPercentage())
                .profileDescription(addMemberCommand.getProfileDescription())
                .projectEndDate(addMemberCommand.getProjectEndDate())
                .projectStartDate(addMemberCommand.getProjectStartDate())
                .yearsOfExperience(addMemberCommand.getYearsOfExperience())
                .skillset(addMemberCommand.getSkillset())
                .build();


    }

    public ProjectMember craeteProjectMemberEntity(@NonNull final MemberAddedEvent memberAddedEvent){

        ProjectMember projectMember = new ProjectMember();
        projectMember.setMemberId(memberAddedEvent.getMemberId());
        projectMember.setMemberName(memberAddedEvent.getMemberName());
        projectMember.setDescription(memberAddedEvent.getProfileDescription());
        projectMember.setSkillset(memberAddedEvent.getSkillset());
        projectMember.setAllocationPercentage(memberAddedEvent.getAllocationPercentage());
        projectMember.setYearsOfExperience(memberAddedEvent.getYearsOfExperience());
        projectMember.setProjectEndDate(memberAddedEvent.getProjectEndDate());
        projectMember.setProjectStartDate(memberAddedEvent.getProjectStartDate());
        return projectMember;


    }
}

