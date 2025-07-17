package com.example.graphqldemo.service;

import com.example.graphqldemo.dto.TaskDto;
import com.example.graphqldemo.dto.TaskInput;
import com.example.graphqldemo.dto.TaskUpdateInput;
import com.example.graphqldemo.entity.Task;
import com.example.graphqldemo.mapper.TaskMapper;
import org.springframework.stereotype.Service;

//import java.time.LocalDateTime;
import java.time.OffsetDateTime;
//import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<TaskDto> findAll() {
        return taskMapper.selectList(null)
                .stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }

    public TaskDto findById(String id) {
        Task task = taskMapper.selectById(Long.parseLong(id));
        if (task == null) {
            return null;
        }
        return TaskDto.fromEntity(task);
    }

    public TaskDto create(TaskInput input) {
        Task task = new Task();
        task.setTitle(input.getTitle());
        task.setDescription(input.getDescription());
        task.setCompleted(false);
        task.setCreatedAt(OffsetDateTime.now());
        
        taskMapper.insert(task);
        return TaskDto.fromEntity(task);
    }

    public TaskDto update(TaskUpdateInput input) {
        Long id = Long.parseLong(input.getId());
        Task task = taskMapper.selectById(id);
        if (task == null) {
            return null;
        }
        
        if (input.getTitle() != null) {
            task.setTitle(input.getTitle());
        }
        
        if (input.getDescription() != null) {
            task.setDescription(input.getDescription());
        }
        
        if (input.getCompleted() != null) {
            task.setCompleted(input.getCompleted());
        }
        
        task.setUpdatedAt(OffsetDateTime.now());
        
        taskMapper.updateById(task);
        return TaskDto.fromEntity(task);
    }

    public boolean delete(String id) {
        int result = taskMapper.deleteById(Long.parseLong(id));
        return result > 0;
    }
} 