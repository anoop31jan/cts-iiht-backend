package com.cts.iiht.taskservice.model;

import lombok.*;

import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetailsDto {

    private String memberId;
    private String taskName;
    private String deliverables;
    private LocalDate taskStartDate;
    private LocalDate  taskEndDate;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private int allocationPercentage;

}
