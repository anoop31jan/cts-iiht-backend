package com.cts.iiht.basedomain.model;

import lombok.*;
import lombok.experimental.*;

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
