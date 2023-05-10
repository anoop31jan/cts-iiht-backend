package com.cts.iiht.taskservice.repository;

import com.cts.iiht.taskservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> getTaskBymemberId(String memberId);
}
