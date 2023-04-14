package com.cts.iiht.basedomain.model;

import lombok.*;

import java.time.*;
import java.util.*;

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
