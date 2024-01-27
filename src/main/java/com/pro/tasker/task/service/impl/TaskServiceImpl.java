package com.pro.tasker.task.service.impl;

import com.google.gson.Gson;
import com.pro.tasker.task.entity.Task;
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
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseGet(null);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        // todo implement logic
        return new Task();
    }

    public boolean deleteTask(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            taskRepository.deleteById(taskId);
            return true;
        } else {
            return false;
        }
    }

    public String serializeTaskToJson(Task createdTask) {
        Gson gson = new Gson();
        return gson.toJson(createdTask);
    }
}
