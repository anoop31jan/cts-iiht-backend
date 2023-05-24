package com.cts.iiht.taskservice.service;

import com.cts.iiht.taskservice.entity.*;
import com.cts.iiht.taskservice.external.*;
import com.cts.iiht.taskservice.external.client.*;
import com.cts.iiht.taskservice.helper.*;
import com.cts.iiht.taskservice.model.*;
import com.cts.iiht.taskservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class QueryService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    TaskServiceHelper taskServiceHelper;


    public List<TaskDetailsDto> getListOfTaskDetails(final String memberId) {

        List<Task> taskList = taskRepository.getTaskBymemberId(memberId);

        ProjectMemberClient projectMemberClient = memberService.getMemberDetails(memberId);

        return taskList.stream()
                .map(task -> taskServiceHelper.prepareTaskDtoObject(task, projectMemberClient))
                .collect(Collectors.toList());

    }
}
