package com.cts.iiht.taskservice.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent implements Serializable {
    public String eventName;
    public String createdAt;
    public String transactionId;
}
