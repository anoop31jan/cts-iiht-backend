package com.cts.iiht.memberservice.repository;

import com.cts.iiht.memberservice.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

public interface MemberRepository extends JpaRepository<ProjectMember,Integer> {

     ProjectMember getProjectMemberBymemberId(String memberId);
     Page<ProjectMember> findAll(Pageable pageable);
}
