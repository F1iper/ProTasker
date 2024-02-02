package com.pro.tasker.priority.service.impl;

import com.pro.tasker.exception.priority.TaskPriorityCreationException;
import com.pro.tasker.exception.priority.TaskPriorityDeletionException;
import com.pro.tasker.exception.priority.TaskPriorityNotFoundException;
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

    @Override
    public TaskPriority createPriority(TaskPriority priority) {
        try {
            return priorityRepository.save(priority);
        } catch (Exception e) {
            throw new TaskPriorityCreationException("Error creating task priority", e);
        }
    }

    @Override
    public TaskPriority updatePriority(Long priorityId, TaskPriority updatedPriority) {
        TaskPriority existingPriority = priorityRepository.findById(priorityId).orElse(null);

        if (existingPriority != null) {
            existingPriority.setName(updatedPriority.getName());

            return priorityRepository.save(existingPriority);
        }

        throw new TaskPriorityNotFoundException(priorityId);
    }

    @Override
    public boolean deletePriority(Long priorityId) {
        try {
            if (priorityRepository.existsById(priorityId)) {
                priorityRepository.deleteById(priorityId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new TaskPriorityDeletionException("Error deleting task priority with id: " + priorityId, e);
        }
    }
}
