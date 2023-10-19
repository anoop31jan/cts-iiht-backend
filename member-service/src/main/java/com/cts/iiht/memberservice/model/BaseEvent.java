package com.cts.iiht.memberservice.model;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent {
    public String eventName;
    public String createdAt;
    public String transactionId;
}
