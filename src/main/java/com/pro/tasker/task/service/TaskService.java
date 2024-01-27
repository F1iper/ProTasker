package com.pro.tasker.task.service;

import com.pro.tasker.task.entity.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long taskId);

    Task updateTask(Long taskId, Task updatedTask);

    boolean deleteTask(Long taskId);

    String serializeTaskToJson(Task createdTask);
}