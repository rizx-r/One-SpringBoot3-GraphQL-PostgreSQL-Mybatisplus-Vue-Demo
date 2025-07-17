<template>
  <div class="task-form">
    <h2>Add New Task</h2>
    <form @submit.prevent="createTask">
      <div class="form-group">
        <label for="title">Title</label>
        <input 
          type="text" 
          id="title" 
          v-model="task.title" 
          required 
          placeholder="Enter task title"
        >
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <textarea 
          id="description" 
          v-model="task.description" 
          placeholder="Enter task description"
        ></textarea>
      </div>
      <button type="submit" :disabled="loading">
        {{ loading ? 'Adding...' : 'Add Task' }}
      </button>
    </form>
  </div>
</template>

<script>
import { reactive, ref } from 'vue'
import { useMutation } from '@vue/apollo-composable'
import { CREATE_TASK } from '../graphql/queries'

export default {
  emits: ['task-created'],
  setup(props, { emit }) {
    const task = reactive({
      title: '',
      description: ''
    })
    
    const loading = ref(false)
    
    const { mutate: createTaskMutation } = useMutation(CREATE_TASK)
    
    const createTask = async () => {
      loading.value = true
      try {
        await createTaskMutation({
          input: {
            title: task.title,
            description: task.description || null
          }
        })
        
        // Reset form
        task.title = ''
        task.description = ''
        
        // Emit event to refresh task list
        emit('task-created')
      } catch (error) {
        console.error('Error creating task:', error)
      } finally {
        loading.value = false
      }
    }
    
    return {
      task,
      loading,
      createTask
    }
  }
}
</script>

<style>
.task-form {
  margin-bottom: 2rem;
  padding-bottom: 2rem;
  border-bottom: 1px solid #eee;
}

h2 {
  margin-top: 0;
  color: #333;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

input, textarea {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

textarea {
  min-height: 100px;
  resize: vertical;
}

button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #45a049;
}

button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}
</style> 