package com.pro.tasker.task.service;

import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long taskId);

    Task updateTaskStatus(Long taskId, TaskStatus newStatus);

    Task updateTaskDescription(Long taskId, String newDescription);

    Task updateTaskTitle(Long taskId, String newTitle);

    boolean deleteTask(Long taskId);

    String serializeTaskToJson(Task createdTask);
}