package com.cts.iiht.memberservice.service;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import javax.servlet.http.*;
import java.net.http.*;
import java.util.*;
import java.util.stream.*;

@Service
public class QueryService {
    @Autowired
    MemberRepository memberRepository;

    public ProjectMember getProjectMemberByMemberId(final String memberId){

    return  memberRepository.getProjectMemberBymemberId(memberId);

    }

    public List<ProjectMemberDto> getAllMembersFromProject(Pageable pageable, HttpServletResponse response){
       final Page<ProjectMember> membersOnPage = memberRepository.findAll(pageable);
       final List<ProjectMember> members = membersOnPage.getContent();
       List<ProjectMemberDto>  listOfProjectMembersDto = new ArrayList<>();
       if (!CollectionUtils.isEmpty(members)) {
           listOfProjectMembersDto = members.stream()
                   .map(member -> new ProjectMemberDto(member.getMemberName(),
                           member.getMemberId(),
                           member.getYearsOfExperience(),
                           member.getSkillset(),
                           member.getDescription(),
                           member.getProjectStartDate(),
                           member.getProjectEndDate(),
                           member.getAllocationPercentage())).collect(Collectors.toList());
           listOfProjectMembersDto.sort(Comparator.comparing(ProjectMemberDto::getYearsOfExperienc).reversed());

       }
        if (membersOnPage.hasNext()){
            Pageable nextPageable1 = pageable.next();
            String nextPageUrl = "/members?page="+ nextPageable1.getPageNumber() + "&size="+nextPageable1.getPageSize();
            response.setHeader(HttpHeaders.LINK, "<"+ nextPageUrl + ">; rel=\"next\"");
        }
        return listOfProjectMembersDto;
    }
}

