package com.cts.iiht.taskservice.controller;

import com.cts.iiht.basedomain.model.*;
import com.cts.iiht.taskservice.external.*;
import com.cts.iiht.taskservice.external.client.*;
import com.cts.iiht.taskservice.model.*;
import com.cts.iiht.taskservice.service.*;
import org.apache.kafka.common.errors.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/taskservice/api/v1")
public class TaskController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private TaskCommandHandler taskCommandHandler;

    @Autowired
    private QueryService queryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @PostMapping("/manager/assign-task")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> assignTask(@Valid @RequestBody AssignTaskCommand assignTaskCommand) {

        ProjectMemberClient memberClient = memberService.getMemberDetails(assignTaskCommand.getMemberId());

        LOGGER.debug("data received from Rest call {} " , memberClient);

        if (Objects.nonNull(memberClient)) {

            if (assignTaskCommand.getTaskEndDate().isBefore(assignTaskCommand.getTaskStartDate())) {
                throw new InvalidRequestException("Task start date can not be before task end date ");
            }
            if (assignTaskCommand.getTaskEndDate().isAfter(memberClient.getProjectEndDate())) {
                throw new InvalidRequestException("Task end date can not be before Project end date ");
            }

            final TaskAssignedEvent taskAssignedEvent = taskCommandHandler.sendMessage(assignTaskCommand);
            APIResponse apiResponse = APIResponse.builder()
                    .success(Boolean.TRUE)
                    .message(" Task created and assigned to team member with id " + assignTaskCommand.getMemberId())
                    .data(taskAssignedEvent)
                    .build();
            return ResponseEntity.ok(apiResponse);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/member/list/{memberId}/taskDetails")
    public ResponseEntity<List<TaskDetailsDto>> getTaskListForMember(@PathVariable String memberId){

         List<TaskDetailsDto> taskDetailsDtos = queryService.getListOfTaskDetails(memberId);

        return ResponseEntity.ok(taskDetailsDtos);

    }

}
