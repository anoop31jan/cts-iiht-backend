package com.cts.iiht.taskservice.external;

import com.cts.iiht.taskservice.external.client.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "member-service",url = "localhost:8081/api/v1")
public interface MemberService {

    @GetMapping("/member/{memberId}")
    ProjectMemberClient getMemberDetails(@PathVariable String memberId);

}
