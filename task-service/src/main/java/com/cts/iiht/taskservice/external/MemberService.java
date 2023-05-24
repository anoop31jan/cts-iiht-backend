package com.cts.iiht.taskservice.external;

import com.cts.iiht.taskservice.external.client.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "member-service")
public interface MemberService {

    @GetMapping("/projectmgmt/api/v1/member/{memberId}")
    ProjectMemberClient getMemberDetails(@PathVariable String memberId);

}
