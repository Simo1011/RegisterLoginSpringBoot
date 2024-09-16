package com.simo.LoginExample.dtos;


import com.simo.LoginExample.model.Task;

import java.time.LocalDate;


public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    private UserDTO user;

    // Constructor
    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.completed = task.isCompleted();
        this.dueDate = task.getDueDate();
        this.user = new UserDTO(task.getUser());
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}