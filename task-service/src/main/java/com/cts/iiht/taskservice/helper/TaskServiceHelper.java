package com.cts.iiht.taskservice.helper;

import com.cts.iiht.taskservice.model.*;
import org.springframework.stereotype.*;

import java.time.*;

@Component
public class TaskServiceHelper {


    public TaskAssignedEvent createTaskAssignedEvent(final AssignTaskCommand assignTaskCommand) {

       return TaskAssignedEvent.builder()
                .eventName("taskAssignedEvent")
                .createdAt(LocalDateTime.now().toString())
                .memberId(assignTaskCommand.getMemberId())
                .taskName(assignTaskCommand.getTaskName())
                .deliverables(assignTaskCommand.getDeliverables())
                .taskStartDate(assignTaskCommand.getTaskStartDate())
                .taskEndDate(assignTaskCommand.getTaskEndDate())
                .build();
    }
}
