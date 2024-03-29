package com.pro.tasker.task.controller;

import com.pro.tasker.config.rabbitmq.RabbitMQConfig;
import com.pro.tasker.messaging.MessageSender;
import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.entity.TaskStatus;
import com.pro.tasker.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Task controller", description = "Rest controller for tasks.")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private MessageSender messageSender;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        String taskJson = taskService.serializeTaskToJson(createdTask);
        messageSender.sendMessage(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                taskJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // TODO: create API interface to simplify controller responses code
    @Operation(
            summary = "Fetch all tasks",
            description = "fetches all task entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestParam TaskStatus newStatus) {
        Task updatedTask = taskService.updateTaskStatus(taskId, newStatus);
        return (updatedTask != null) ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{taskId}/description")
    public ResponseEntity<Task> updateTaskDescription(@PathVariable Long taskId, @RequestParam String newDescription) {
        Task updatedTask = taskService.updateTaskDescription(taskId, newDescription);
        return (updatedTask != null) ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
    }


    @PatchMapping("/{taskId}/title")
    public ResponseEntity<Task> updateTaskTitle(@PathVariable Long taskId, @RequestParam String newTitle) {
        Task updatedTask = taskService.updateTaskTitle(taskId, newTitle);
        return (updatedTask != null) ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
    }

}