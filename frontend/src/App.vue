<template>
  <div class="app">
    <header>
      <h1>Task Manager</h1>
    </header>
    <main>
      <div class="container">
        <TaskForm @task-created="refreshTasks" />
        <TaskList 
          :tasks="tasks" 
          @task-updated="refreshTasks" 
          @task-deleted="refreshTasks" 
        />
      </div>
    </main>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useQuery } from '@vue/apollo-composable'
import { GET_TASKS } from './graphql/queries'
import TaskForm from './components/TaskForm.vue'
import TaskList from './components/TaskList.vue'

export default {
  components: {
    TaskForm,
    TaskList
  },
  setup() {
    const tasks = ref([])
    
    const { result, loading, error, refetch } = useQuery(GET_TASKS)
    
    const refreshTasks = async () => {
      await refetch()
      if (result.value) {
        tasks.value = result.value.tasks
      }
    }
    
    onMounted(async () => {
      await refreshTasks()
    })
    
    return {
      tasks,
      loading,
      error,
      refreshTasks
    }
  }
}
</script>

<style>
.app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
}

header {
  margin-bottom: 2rem;
  text-align: center;
}

h1 {
  color: #333;
}

.container {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 2rem;
}
</style> 