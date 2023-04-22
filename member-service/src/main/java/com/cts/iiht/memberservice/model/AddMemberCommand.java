package com.cts.iiht.memberservice.model;


import lombok.*;

import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberCommand {
    @NotEmpty
    private String memberName;
    @NotEmpty
    private String memberId;
    @Positive(message = "Member must have at least 5 years of experience")
    @Min(5)
    private int yearsOfExperience;
    @NotNull
    @Size(min =3 , message = "Member must possess at least 3 skillSets")
    private List<String> skillset;
    @NotEmpty
    private String profileDescription;
    @NotNull
    private LocalDate projectStartDate;
    @NotNull
    private LocalDate projectEndDate;
    @Positive(message = "value should be between 1 to 100")
    @Min(1)
    @Max(100)
    private int allocationPercentage;
}
