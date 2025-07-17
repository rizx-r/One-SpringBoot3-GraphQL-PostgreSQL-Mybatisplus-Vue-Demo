package com.example.graphqldemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Data
@TableName("tasks")
public class Task {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String title;
    
    private String description;
    
    private Boolean completed;
    
    private OffsetDateTime createdAt;
    
    private OffsetDateTime updatedAt;
    
    public Task() {
        this.completed = false;
        this.createdAt = OffsetDateTime.now();
    }
} 