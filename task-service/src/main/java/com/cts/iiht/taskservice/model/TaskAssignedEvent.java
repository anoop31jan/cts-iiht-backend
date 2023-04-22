package com.cts.iiht.taskservice.model;

import com.cts.iiht.basedomain.model.*;

import java.time.*;

public class TaskAssignedEvent extends BaseEvent {

    private String memberId;
    private String taskName;
    private String deliverables;
    private LocalDate taskStartDate;
    private LocalDate  taskEndDate;
}
