package com.cts.iiht.basedomain.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {
    private boolean success;
    private String message;
    private Object data;

}
