<template>
  <div class="flex flex-col h-full">
    <!-- Header -->
    <div class="flex items-center justify-between p-4 bg-gray-100 dark:bg-gray-800 rounded-t-lg">
      <h2 class="text-2xl font-bold text-gray-800 dark:text-gray-100 flex items-center">
        <Icon name="clipboardList" variant="solid" className="h-6 w-6 mr-2 text-primary" />
        Tasks
      </h2>
      <!-- Add Task Button -->
      <button @click="openAddTaskModal" class="flex items-center px-3 py-2 bg-secondary text-white rounded-lg hover:bg-secondary-dark transition">
        <Icon name="plusCircle" variant="solid" className="h-5 w-5 mr-1" />
        Add Task
      </button>
    </div>
    <!-- Tasks List -->
    <div class="flex-1 overflow-y-auto p-4 bg-gray-50 dark:bg-gray-600">
      <div v-if="tasks.length === 0" class="text-center text-gray-500 dark:text-gray-300">
        No tasks available. Start by adding a new task!
      </div>
      <TaskItem v-for="task in tasks" :key="task.id" :task="task" />
    </div>

    <!-- Add Task Modal -->
    <AddTaskModal :project="project" v-if="showAddTaskModal" @close="showAddTaskModal = false" @task-added="handleTaskAdded" />
  </div>
</template>

<script>
import TaskItem from './TaskItem.vue';
import Icon from '@/components/Icons/Icon.vue';
import AddTaskModal from './AddTaskModal.vue'; // We'll create this component next

export default {
  name: 'TasksTab',
  components: {
    TaskItem,
    Icon,
    AddTaskModal
  },
  props: {
    project: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      showAddTaskModal: false
    }
  },
  computed: {
    tasks() {
      return this.project.tasks;
    }
  },
  methods: {
    openAddTaskModal() {
      this.showAddTaskModal = true;
    },
    handleTaskAdded(newTask) {
      this.$emit('task-added', newTask);
      this.showAddTaskModal = false;
    }
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
