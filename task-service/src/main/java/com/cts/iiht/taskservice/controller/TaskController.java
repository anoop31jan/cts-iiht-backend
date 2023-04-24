package com.cts.iiht.taskservice.controller;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.taskservice.external.*;
import com.cts.iiht.taskservice.external.client.*;
import com.cts.iiht.taskservice.model.*;
import com.cts.iiht.taskservice.service.*;
import org.apache.kafka.common.errors.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("api/v1")
public class TaskController {
    @Autowired
    private  MemberService memberService;
    @Autowired
    private TaskCommandHandler taskCommandHandler;

    @PostMapping("/assign-task")
    public ResponseEntity<Object> assignTask(@Valid @RequestBody AssignTaskCommand assignTaskCommand){

        ProjectMemberClient memberClient  = memberService.getMemberDetails(assignTaskCommand.getMemberId());

        System.out.println("data received from Rest call "+ memberClient);

        if (Objects.nonNull(memberClient)) {
            System.out.println(" Member name "+ memberClient.getMemberName());
            System.out.println(" TASK start date "+ assignTaskCommand.getTaskStartDate());
            System.out.println(" Task end date "+ assignTaskCommand.getTaskEndDate());
            System.out.println(" Proje end date "+ memberClient.getProjectEndDate());

            if (assignTaskCommand.getTaskEndDate().isBefore(assignTaskCommand.getTaskStartDate())) {
                throw new InvalidRequestException("Task start date can not be before task end date ");
            }
            if (assignTaskCommand.getTaskEndDate().isAfter(memberClient.getProjectEndDate())){
                throw new InvalidRequestException("Task end date can not be before Project end date ");
            }

            taskCommandHandler.sendMessage(assignTaskCommand);
            APIResponse apiResponse = APIResponse.builder()
                    .success(Boolean.TRUE)
                    .message(" Task created and assigned to team member with id " + assignTaskCommand.getMemberId())
                    .build();
            return ResponseEntity.ok(apiResponse);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
