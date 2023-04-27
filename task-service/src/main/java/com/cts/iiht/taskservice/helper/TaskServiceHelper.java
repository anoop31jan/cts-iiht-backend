package com.cts.iiht.taskservice.helper;

import com.cts.iiht.taskservice.entity.*;
import com.cts.iiht.taskservice.model.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

import static com.cts.iiht.basedomain.constant.ProjectTrackerConstant.TASK_ASSIGNED_EVENT;

@Component
public class TaskServiceHelper {


    public TaskAssignedEvent createTaskAssignedEvent(final AssignTaskCommand assignTaskCommand) {

        return TaskAssignedEvent.builder()
                .eventName(TASK_ASSIGNED_EVENT)
                .transactionId(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now().toString())
                .memberId(assignTaskCommand.getMemberId())
                .taskName(assignTaskCommand.getTaskName())
                .deliverables(assignTaskCommand.getDeliverables())
                .taskStartDate(assignTaskCommand.getTaskStartDate())
                .taskEndDate(assignTaskCommand.getTaskEndDate())
                .build();
    }

    public Task createTaskAssignedEntity(final TaskAssignedEvent taskAssignedEvent) {

        Task task = new Task();
        task.setTaskName(taskAssignedEvent.getTaskName());
        task.setDeliverables(taskAssignedEvent.getDeliverables());
        task.setMemberId(taskAssignedEvent.getMemberId());
        task.setTaskStartDate(taskAssignedEvent.getTaskStartDate());
        task.setTaskEndDate(taskAssignedEvent.getTaskEndDate());

        return task;
    }
}
