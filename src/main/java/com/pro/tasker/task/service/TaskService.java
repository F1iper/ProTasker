package com.pro.tasker.task.service;

import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

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
        // todo implement correct logic
        return new Task();
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
