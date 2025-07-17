package com.example.graphqldemo.dto;

import lombok.Data;

@Data
public class TaskUpdateInput {
    private String id;
    private String title;
    private String description;
    private Boolean completed;
} 