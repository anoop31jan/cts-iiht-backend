package com.cts.iiht.memberservice.controller;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.model.*;
import com.cts.iiht.memberservice.service.*;
import org.apache.commons.lang3.*;
import org.apache.kafka.common.errors.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.validation.*;
import java.util.*;

import static com.cts.iiht.basedomain.constant.ProjectTrackerConstant.*;

@RestController
@RequestMapping("/projectmgmt/api/v1")
public class MemberServiceController {


    private AddMemberCommandHandler addMemberCommandHandler;

    @Autowired
    private QueryService queryService;

    public MemberServiceController(AddMemberCommandHandler addMemberCommandHandler) {
        this.addMemberCommandHandler = addMemberCommandHandler;
    }

    @PostMapping("/manager/add-member")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<APIResponse> addProjectMember(@Valid @RequestBody AddMemberCommand addMemberCommand){

        ProjectMember projectMember = queryService.getProjectMemberByMemberId(addMemberCommand.getMemberId());

        if (Objects.nonNull(projectMember)){
            throw new InvalidRequestException(ERROR_MESSAGE_MEMBER_ALREADY_EXIST);
        }

        if (addMemberCommand.getProjectEndDate().isBefore(addMemberCommand.getProjectStartDate())){
            throw new InvalidRequestException(ERROR_MESSAGE_PROJECT_START_DATE);
        }
        final MemberAddedEvent memberAddedEvent = addMemberCommandHandler.sendMessage(addMemberCommand);

        APIResponse apiResponse = APIResponse.builder()
                .success(Boolean.TRUE)
                .message(" Team member added successfully")
                .data(memberAddedEvent)
                .build();
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ProjectMember> getMemberDetails(@PathVariable String memberId) {
        if (StringUtils.isNoneBlank(memberId)) {
            ProjectMember projectMember = queryService.getProjectMemberByMemberId(memberId);
            if (Objects.nonNull(projectMember)) {

                return ResponseEntity.ok(projectMember);
            }
            throw new InvalidRequestException(ERROR_MESSAGE_MEMBER_NOT_FOUND + memberId);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/manager/list/memberdetails")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllMembers(@PathVariable (required = false) String memberDetail,
            @PageableDefault(size = 20, sort = "id") Pageable pageable, HttpServletResponse response) {

        List<ProjectMemberDto> listOfAllMembers = queryService.getAllMembersFromProject(pageable, response);
        TeamMembers teamMembers = new TeamMembers();
        teamMembers.setTeamMembers(listOfAllMembers);
        return ResponseEntity.ok(teamMembers);

    }

    @PutMapping("/manager/update/{memberId}/allocationpercentage")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateAllocationPercentage(@PathVariable String memberId){

        ProjectMember projectMember = queryService.getProjectMemberByMemberId(memberId);
        if (Objects.isNull(projectMember)){
            throw new InvalidRequestException(ERROR_MESSAGE_MEMBER_NOT_FOUND + memberId);
        }
        queryService.updateMemberAllocationpercentage(projectMember);
        APIResponse apiResponse = APIResponse.builder()
                .success(Boolean.TRUE)
                .message(" Allocation percentage updated successfully")
                .data(projectMember)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
