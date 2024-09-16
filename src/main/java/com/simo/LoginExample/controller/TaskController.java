package com.simo.LoginExample.controller;


import com.simo.LoginExample.dtos.TaskDTO;
import com.simo.LoginExample.model.Task;
import com.simo.LoginExample.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all tasks for the logged-in user
    @GetMapping
    public List<TaskDTO> getTasks(Authentication authentication) {
        String username = authentication.getName();
        List<TaskDTO> tasks = taskService.getAllTasksByUser(username);
        return tasks;
    }

    // Create a new task
    @PostMapping
    public TaskDTO createTask(@RequestBody Task task, Authentication authentication) {
        String username = authentication.getName();
        Task createdTask = taskService.createTask(task, username);
        if (createdTask != null) {
            return new TaskDTO(createdTask);
        }
        // Handle error appropriately (return error response)
        return null;
    }

    // Update an existing task
    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            return new TaskDTO(updatedTask);
        }
        // Handle error appropriately (return error response)
        return null;
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO taskDTO = taskService.getTaskById(id);
        if (taskDTO != null) {
            return ResponseEntity.ok(taskDTO);  // Return task if found
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if not found
        }
    }
}