package com.cts.iiht.memberservice.controller;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.memberservice.entity.*;
import com.cts.iiht.memberservice.model.*;
import com.cts.iiht.memberservice.service.*;
import org.apache.kafka.common.errors.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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

        addMemberCommandHandler.sendMessage(addMemberCommand);

        APIResponse apiResponse = APIResponse.builder()
                .success(Boolean.TRUE)
                .message(" Team member added successfully")
                .build();
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/members")
    public ResponseEntity<List<ProjectMemberDto>> getAllMembers(){

        List<ProjectMemberDto> listOfAllMembers = queryService.getAllMembersFromProject();



        return ResponseEntity.ok(listOfAllMembers);

    }

}
