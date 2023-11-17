package com.cts.iiht.taskservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class TaskDoc {
@Id
    private String id;
    private String taskName;
    private String deliverables;
    private LocalDate taskStartDate;
    private LocalDate taskEndDate;
    private String memberId;
}
