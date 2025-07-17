<template>
  <div class="task-list">
    <h2>Tasks</h2>
    <div v-if="tasks.length === 0" class="empty-state">
      No tasks found. Add a new task to get started.
    </div>
    <ul v-else>
      <li v-for="task in tasks" :key="task.id" class="task-item">
        <div class="task-header">
          <div class="task-title">
            <input 
              type="checkbox" 
              :checked="task.completed" 
              @change="toggleTaskStatus(task)"
            >
            <h3 :class="{ completed: task.completed }">{{ task.title }}</h3>
          </div>
          <button class="delete-btn" @click="deleteTask(task.id)">
            Delete
          </button>
        </div>
        <p v-if="task.description" class="task-description">
          {{ task.description }}
        </p>
        <div class="task-meta">
          <span>Created: {{ formatDate(task.createdAt) }}</span>
          <span v-if="task.updatedAt">
            | Updated: {{ formatDate(task.updatedAt) }}
          </span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { useMutation } from '@vue/apollo-composable'
import { UPDATE_TASK, DELETE_TASK } from '../graphql/queries'

export default {
  props: {
    tasks: {
      type: Array,
      required: true
    }
  },
  emits: ['task-updated', 'task-deleted'],
  setup(props, { emit }) {
    const { mutate: updateTaskMutation } = useMutation(UPDATE_TASK)
    const { mutate: deleteTaskMutation } = useMutation(DELETE_TASK)
    
    const toggleTaskStatus = async (task) => {
      try {
        await updateTaskMutation({
          input: {
            id: task.id,
            completed: !task.completed
          }
        })
        emit('task-updated')
      } catch (error) {
        console.error('Error updating task:', error)
      }
    }
    
    const deleteTask = async (id) => {
      if (confirm('Are you sure you want to delete this task?')) {
        try {
          await deleteTaskMutation({ id })
          emit('task-deleted')
        } catch (error) {
          console.error('Error deleting task:', error)
        }
      }
    }
    
    const formatDate = (dateString) => {
      if (!dateString) return ''
      const date = new Date(dateString)
      return date.toLocaleString()
    }
    
    return {
      toggleTaskStatus,
      deleteTask,
      formatDate
    }
  }
}
</script>

<style>
.task-list {
  margin-top: 1rem;
}

.empty-state {
  text-align: center;
  padding: 2rem;
  color: #777;
  font-style: italic;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.task-item {
  margin-bottom: 1.5rem;
  padding: 1rem;
  border: 1px solid #eee;
  border-radius: 4px;
  background-color: #f9f9f9;
}

.task-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.5rem;
}

.task-title {
  display: flex;
  align-items: center;
}

.task-title h3 {
  margin: 0 0 0 0.5rem;
}

.completed {
  text-decoration: line-through;
  color: #888;
}

.task-description {
  margin: 0.5rem 0;
  color: #555;
}

.task-meta {
  font-size: 0.8rem;
  color: #888;
  margin-top: 0.5rem;
}

.delete-btn {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 0.3rem 0.6rem;
  font-size: 0.8rem;
  border-radius: 4px;
  cursor: pointer;
}

.delete-btn:hover {
  background-color: #d32f2f;
}
</style> 