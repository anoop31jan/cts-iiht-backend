package com.cts.iiht.memberservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberDoc {
    private String memberName;
    @Id
    private String memberId;
    private int yearsOfExperience;
    private List<String> skillset = new ArrayList<>();
    private String description;
    private String projectStartDate;
    private String projectEndDate;
    private int allocationPercentage;
}
