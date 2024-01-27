package com.pro.tasker.priority.controller;

import com.pro.tasker.priority.entity.TaskPriority;
import com.pro.tasker.priority.service.TaskPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/priorities")
public class TaskPriorityController  {

    private final TaskPriorityService priorityService;

    @Autowired
    public TaskPriorityController(TaskPriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<TaskPriority>> getAllPriorities() {
        List<TaskPriority> priorities = priorityService.getAllPriorities();
        return ResponseEntity.ok(priorities);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskPriority> createPriority(@RequestBody TaskPriority priority) throws URISyntaxException {
        TaskPriority createdPriority = priorityService.createPriority(priority);
        return ResponseEntity.created(new URI("/priorities/create/" + createdPriority.getId())).body(createdPriority);
    }

    @PatchMapping("/update/{priorityId}")
    public ResponseEntity<TaskPriority> updatePriority(
            @PathVariable Long priorityId, @RequestBody TaskPriority updatedPriority) {
        TaskPriority updated = priorityService.updatePriority(priorityId, updatedPriority);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{priorityId}")
    public ResponseEntity<String> deletePriority(@PathVariable Long priorityId) {
        boolean deletionSuccessful = priorityService.deletePriority(priorityId);

        if (deletionSuccessful) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}