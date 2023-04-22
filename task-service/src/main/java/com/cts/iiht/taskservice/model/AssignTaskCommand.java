package com.cts.iiht.taskservice.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignTaskCommand {

    @NotEmpty
    private String memberId;
    @NotEmpty
    private String taskName;
    @NotEmpty
    private String deliverables;
    @NotNull
    private LocalDate taskStartDate;
    @NotNull
    private LocalDate  taskEndDate;
}
