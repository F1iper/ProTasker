package com.pro.tasker.task.controller;

import com.google.gson.Gson;
import com.pro.tasker.messaging.MessageSender;
import com.pro.tasker.messaging.config.RabbitMQConfig;
import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private MessageSender messageSender;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);

        Gson gson = new Gson();
        String taskJson = gson.toJson(createdTask);

        messageSender.sendMessage(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, taskJson);

        return createdTask;
    }

    @GetMapping("/getAll")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/get/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PatchMapping("/update/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        return taskService.updateTask(taskId, updatedTask);
    }

    @DeleteMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "Task deleted successfully";
    }
}