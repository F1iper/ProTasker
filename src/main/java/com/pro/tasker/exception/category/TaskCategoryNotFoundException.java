package com.pro.tasker.exception.category;

public class TaskCategoryNotFoundException extends RuntimeException {
    public TaskCategoryNotFoundException(Long id) {
        super("Task category with ID: [" + id + "] not found.");
    }
}