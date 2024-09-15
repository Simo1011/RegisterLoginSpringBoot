package com.simo.LoginExample.repository;

import com.simo.LoginExample.model.Task;
import com.simo.LoginExample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}