package com.pro.tasker.priority.repository;

import com.pro.tasker.priority.entity.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskPriorityRepository extends JpaRepository<TaskPriority, Long> {
}