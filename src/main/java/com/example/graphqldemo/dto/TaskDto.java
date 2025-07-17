package com.example.graphqldemo.dto;

import com.example.graphqldemo.entity.Task;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class TaskDto {
    private String id;
    private String title;
    private String description;
    private Boolean completed;
    private String createdAt;
    private String updatedAt;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    public static TaskDto fromEntity(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId().toString());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        dto.setCreatedAt(task.getCreatedAt().format(FORMATTER));
        
        if (task.getUpdatedAt() != null) {
            dto.setUpdatedAt(task.getUpdatedAt().format(FORMATTER));
        }
        
        return dto;
    }
} 