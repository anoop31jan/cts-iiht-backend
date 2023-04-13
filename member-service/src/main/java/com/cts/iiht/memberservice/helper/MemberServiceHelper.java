package com.cts.iiht.memberservice.helper;

import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.model.*;
import org.springframework.stereotype.*;

import java.time.*;

import static com.cts.iiht.basedomain.constant.ProjectTrackerConstant.MEMBER_CREATED_EVENT;

@Component
public class MemberServiceHelper {

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

    public ProjectMember craeteProjectMemberEntity(MemberAddedEvent memberAddedEvent){

        ProjectMember projectMember = new ProjectMember();
        projectMember.setMemberId(memberAddedEvent.getMemberId());
        projectMember.setMemberName(memberAddedEvent.getMemberName());
        projectMember.setDescription(memberAddedEvent.getProfileDescription());
        projectMember.setSkillset(memberAddedEvent.getSkillset());
        projectMember.setAllocationPercentage(memberAddedEvent.getAllocationPercentage());
        projectMember.setYearsOfExperienc(memberAddedEvent.getYearsOfExperienc());
        projectMember.setProjectEndDate(memberAddedEvent.getProjectEndDate());
        projectMember.setProjectStartDate(memberAddedEvent.getProjectStartDate());
        return projectMember;


    }
}

