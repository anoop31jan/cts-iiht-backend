package com.cts.iiht.taskservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="task_name", nullable = false)
    private String taskName;

    @Column(name ="deliverables", nullable = false)
    private String deliverables;

    @Column(name ="task_start_date", nullable = false)
    private LocalDate taskStartDate;

    @Column(name ="task_end_date", nullable = false)
    private LocalDate taskEndDate;

    @Column(name = "member_id" , nullable = false)
    private String memberId;
}
