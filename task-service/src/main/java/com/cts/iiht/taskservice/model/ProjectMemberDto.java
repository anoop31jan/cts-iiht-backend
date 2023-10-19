package com.cts.iiht.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDto {
    private String memberName;
    private String memberId;
    private int yearsOfExperienc;
    private List<String> skillset;
    private String profileDescription;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private int allocationPercentage;
}
