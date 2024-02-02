package com.pro.tasker.task.service.impl;

import com.google.gson.Gson;
import com.pro.tasker.exception.task.TaskCreationException;
import com.pro.tasker.exception.task.TaskDeletionException;
import com.pro.tasker.exception.task.TaskNotFoundException;
import com.pro.tasker.exception.task.TaskSerializationException;
import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.entity.TaskStatus;
import com.pro.tasker.task.repository.TaskRepository;
import com.pro.tasker.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskCreationException("Error creating task", e);
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with ID: [" + taskId + "] not found."));
    }


    @Override
    public Task updateTaskStatus(Long taskId, TaskStatus newStatus) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingTask != null) {
            existingTask.setStatus(newStatus);
            return taskRepository.save(existingTask);
        } else {
            throw new TaskNotFoundException("Task with ID [" + taskId + "] not found.");
        }
    }

    @Override
    public Task updateTaskDescription(Long taskId, String newDescription) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingTask != null) {
            existingTask.setDescription(newDescription);
            return taskRepository.save(existingTask);
        } else {
            throw new TaskNotFoundException("Task with ID [" + taskId + "] not found.");
        }
    }

    @Override
    public Task updateTaskTitle(Long taskId, String newTitle) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingTask != null) {
            existingTask.setTitle(newTitle);
            return taskRepository.save(existingTask);
        } else {
            throw new TaskNotFoundException("Task with ID [" + taskId + "] not found.");
        }
    }

    public boolean deleteTask(Long taskId) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(taskId);
            if (taskOptional.isPresent()) {
                taskRepository.deleteById(taskId);
                return true;
            } else {
                 throw new TaskNotFoundException("Task with ID: [" + taskId + "] not found.");
            }
        } catch (Exception e) {
            throw new TaskDeletionException("Error deleting task with ID: [" + taskId + "]", e);
        }
    }

    public String serializeTaskToJson(Task createdTask) {
        try {
            Gson gson = new Gson();
            return gson.toJson(createdTask);
        } catch (Exception e) {
            // todo: add logger
            throw new TaskSerializationException("Error serializing task to JSON", e);
        }
    }
}
