package com.cts.iiht.basedomain.model;

import lombok.*;
import lombok.experimental.*;


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
