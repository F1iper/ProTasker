package com.pro.tasker.priority.service.impl;

import com.pro.tasker.priority.entity.TaskPriority;
import com.pro.tasker.priority.repository.TaskPriorityRepository;
import com.pro.tasker.priority.service.TaskPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskPriorityServiceImpl implements TaskPriorityService {

    private final TaskPriorityRepository priorityRepository;

    @Autowired
    public TaskPriorityServiceImpl(TaskPriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<TaskPriority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    public TaskPriority createPriority(TaskPriority priority) {
        return priorityRepository.save(priority);
    }

    public TaskPriority updatePriority(Long priorityId, TaskPriority updatedPriority) {
        // todo: implement logic
        return null;
    }

    public boolean deletePriority(Long priorityId) {
        if (priorityRepository.existsById(priorityId)) {
            priorityRepository.deleteById(priorityId);
            return true;
        } else {
            return false;
        }
    }
}
