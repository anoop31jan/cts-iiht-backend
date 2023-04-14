package com.cts.iiht.memberservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name="members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
    private String memberName;
    @Column(name="member_id",nullable = false, unique = true)
    private String memberId;
    @Column(name="years_of_experience", nullable = false)
    private int yearsOfExperience;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="skillset",nullable = false)
    private List<String> skillset = new ArrayList<>();
    @Column(name="description")
    private String description;
    @Column(name = "project_start_date",nullable =false)
    private LocalDate projectStartDate;
    @Column(name = "project_end_date",nullable = false)
    private LocalDate projectEndDate;
    @Column(name = "allocation_percentage",nullable =false)
    private int allocationPercentage;
}
