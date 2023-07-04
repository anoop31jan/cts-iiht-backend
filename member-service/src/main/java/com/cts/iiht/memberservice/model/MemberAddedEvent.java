package com.cts.iiht.memberservice.model;

import com.cts.iiht.basedomain.model.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.time.*;
import java.util.*;

@Data
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddedEvent extends BaseEvent implements Serializable {
    private String memberName;
    private String memberId;
    private int yearsOfExperience;
    private List<String> skillset;
    private String profileDescription;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private int allocationPercentage;
}
