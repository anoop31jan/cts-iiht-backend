package com.cts.iiht.memberservice.service;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.util.*;
import java.util.stream.*;

@Service
public class QueryService {
    @Autowired
    MemberRepository memberRepository;

    public ProjectMember getProjectMemberByMemberId(final String memberId){

    return  memberRepository.getProjectMemberBymemberId(memberId);

    }

    public List<ProjectMemberDto> getAllMembersFromProject(){
       final List<ProjectMember> members = memberRepository.findAll();
       List<ProjectMemberDto>  listOfProjectMembersDto = new ArrayList<>();
       if (!CollectionUtils.isEmpty(members)) {
           listOfProjectMembersDto = members.stream()
                   .map(member -> new ProjectMemberDto(member.getMemberName(),
                           member.getMemberId(),
                           member.getYearsOfExperienc(),
                           member.getSkillset(),
                           member.getDescription(),
                           member.getProjectStartDate(),
                           member.getProjectEndDate(),
                           member.getAllocationPercentage())).collect(Collectors.toList());
           listOfProjectMembersDto.sort(Comparator.comparing(ProjectMemberDto::getYearsOfExperienc).reversed());

       }
        return listOfProjectMembersDto;
    }
}

