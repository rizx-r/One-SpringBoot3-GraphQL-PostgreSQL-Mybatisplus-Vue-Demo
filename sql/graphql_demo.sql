CREATE TABLE task (
                      id SERIAL PRIMARY KEY,  -- 自增主键[3,4](@ref)
                      title VARCHAR(255) NOT NULL,  -- 标题（非空）[3](@ref)
                      description TEXT,  -- 描述（可为空）
                      completed BOOLEAN NOT NULL DEFAULT FALSE,  -- 完成状态（布尔类型）[6,7](@ref)
                      created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 创建时间（自动生成）[4](@ref)
--                       updated_at TIMESTAMPTZ  -- 更新时间（由应用层维护）
                      updated_at TIMESTAMPTZ  -- 更新时间（由应用层维护）
);

CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_task_modtime
    BEFORE UPDATE ON task
    FOR EACH ROW EXECUTE FUNCTION update_modified_column();

CREATE INDEX idx_task_completed ON task(completed);  -- 加速状态查询[4](@ref)
CREATE INDEX idx_task_created ON task(created_at);  -- 加速时间范围查询