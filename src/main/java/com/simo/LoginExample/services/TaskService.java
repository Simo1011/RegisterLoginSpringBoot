package com.simo.LoginExample.services;


import com.simo.LoginExample.dtos.TaskDTO;
import com.simo.LoginExample.model.Task;
import com.simo.LoginExample.model.User;
import com.simo.LoginExample.repository.TaskRepository;
import com.simo.LoginExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskDTO> getAllTasksByUser(String username) {
        User user = userRepository.findByUsername(username);  // Directly get User
        if (user != null) {
            return taskRepository.findByUser(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
        }
        return null;  // Or handle appropriately (return empty list or throw exception)
    }

    public Task createTask(Task task, String username) {
        User user = userRepository.findByUsername(username);  // Directly get User
        if (user != null) {
            task.setUser(user);  // Associate task with the user
            return taskRepository.save(task);
        }
        return null;  // Or handle appropriately (throw exception or error response)
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            existingTask.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(existingTask);
        }
        return null;  // Or handle appropriately (throw exception or error response)
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);  // Delete task by ID
    }
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            return new TaskDTO(task);  // Return the DTO if task is found
        }
        return null;  // Return null if task not found
    }
}