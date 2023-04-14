package com.cts.iiht.memberservice.controller;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.model.*;
import com.cts.iiht.memberservice.service.*;
import org.apache.kafka.common.errors.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("api/v1")
public class MemberServiceController {


    private AddMemberCommandHandler addMemberCommandHandler;

    @Autowired
    private QueryService queryService;

    public MemberServiceController(AddMemberCommandHandler addMemberCommandHandler) {
        this.addMemberCommandHandler = addMemberCommandHandler;
    }

    @PostMapping("/addMember")
    public ResponseEntity<APIResponse> addProjectMember(@Valid @RequestBody AddMemberCommand addMemberCommand){

        ProjectMember projectMember = queryService.getProjectMemberByMemberId(addMemberCommand.getMemberId());

        if (Objects.nonNull(projectMember)){
            throw new InvalidRequestException("Member already exist in the team with same member_id");
        }

        if (addMemberCommand.getProjectEndDate().isBefore(addMemberCommand.getProjectStartDate())){
            throw new InvalidRequestException("Project end date can not be before project start date");
        }
        addMemberCommandHandler.sendMessage(addMemberCommand);

        APIResponse apiResponse = APIResponse.builder()
                .success(Boolean.TRUE)
                .message(" Team member added successfully")
                .build();
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/members")
    public ResponseEntity<List<ProjectMemberDto>> getAllMembers(
            @PageableDefault(size = 20, sort = "id") Pageable pageable, HttpServletResponse response) {

        List<ProjectMemberDto> listOfAllMembers = queryService.getAllMembersFromProject(pageable, response);
        return ResponseEntity.ok(listOfAllMembers);

    }

}
