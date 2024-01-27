package com.pro.tasker.priority.service;

import com.pro.tasker.priority.entity.TaskPriority;

import java.util.List;

public interface TaskPriorityService {

    List<TaskPriority> getAllPriorities();

    TaskPriority createPriority(TaskPriority priority);

    TaskPriority updatePriority(Long priorityId, TaskPriority updatedPriority);

    boolean deletePriority(Long priorityId);
}