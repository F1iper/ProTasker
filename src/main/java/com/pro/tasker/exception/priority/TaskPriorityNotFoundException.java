package com.pro.tasker.exception.priority;

public class TaskPriorityNotFoundException extends RuntimeException {

    public TaskPriorityNotFoundException(Long priorityId) {
        super("Task priority for ID: [" + priorityId + "] not found.");
    }
}