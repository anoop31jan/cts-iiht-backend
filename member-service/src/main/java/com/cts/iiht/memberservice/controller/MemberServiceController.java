package com.cts.iiht.memberservice.controller;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.memberservice.model.*;
import com.cts.iiht.memberservice.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("api/v1")
public class MemberServiceController {


    private AddMemberCommandHandler addMemberCommandHandler;

    public MemberServiceController(AddMemberCommandHandler addMemberCommandHandler) {
        this.addMemberCommandHandler = addMemberCommandHandler;
    }

    @PostMapping("/addMember")
    public ResponseEntity<APIResponse> addProjectMember(@Valid @RequestBody AddMemberCommand addMemberCommand){

        addMemberCommandHandler.sendMessage(addMemberCommand);

        APIResponse apiResponse = APIResponse.builder()
                .success(Boolean.TRUE)
                .message(" Team member added successfully")
                .build();
        return ResponseEntity.ok(apiResponse);


    }

}
