package com.example.graphqldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.graphqldemo.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
} 