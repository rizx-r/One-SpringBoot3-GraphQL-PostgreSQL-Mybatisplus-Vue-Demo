package com.example.graphqldemo.controller;

import com.example.graphqldemo.dto.TaskDto;
import com.example.graphqldemo.dto.TaskInput;
import com.example.graphqldemo.dto.TaskUpdateInput;
import com.example.graphqldemo.service.TaskService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @QueryMapping
    public List<TaskDto> tasks() {
        return taskService.findAll();
    }

    @QueryMapping
    public TaskDto task(@Argument String id) {
        return taskService.findById(id);
    }

    @MutationMapping
    public TaskDto createTask(@Argument TaskInput input) {
        return taskService.create(input);
    }

    @MutationMapping
    public TaskDto updateTask(@Argument TaskUpdateInput input) {
        return taskService.update(input);
    }

    @MutationMapping
    public boolean deleteTask(@Argument String id) {
        return taskService.delete(id);
    }
} 