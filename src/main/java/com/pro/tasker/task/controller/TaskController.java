package com.pro.tasker.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.tasker.messaging.config.RabbitMQConfig;
import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.service.TaskService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskJson;
        try {
            taskJson = objectMapper.writeValueAsString(createdTask);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Task to JSON", e);
        }

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                taskJson
        );
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
        Task updatedTaskResult = taskService.updateTask(taskId, updatedTask);

        // Send message to RabbitMQ when a task is updated
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                "Task updated: " + updatedTaskResult.getId()
        );

        return updatedTaskResult;
    }

    @DeleteMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "Task deleted successfully";
    }
}