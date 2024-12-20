<template>
  <transition name="fade">
    <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-lg w-96 transform transition-transform duration-300 scale-100">
        <h2 class="text-2xl font-bold mb-4 text-gray-800 dark:text-gray-100 flex items-center">
          <Icon name="plusCircle" variant="solid" className="h-6 w-6 mr-2 text-primary" />
          Add New Task
        </h2>
        <form @submit.prevent="submitTask" class="space-y-4">
          <!-- Task Title -->
          <div>
            <label class="block mb-1 text-gray-700 dark:text-gray-200">Title</label>
            <input type="text" v-model="title" required
                   class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary dark:bg-gray-700 dark:border-gray-600 dark:text-gray-200" />
          </div>

          <!-- Task Description -->
          <div>
            <label class="block mb-1 text-gray-700 dark:text-gray-200">Description</label>
            <textarea v-model="description" required
                      class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary dark:bg-gray-700 dark:border-gray-600 dark:text-gray-200"></textarea>
          </div>

          <!-- Deadline -->
          <div>
            <label class="block mb-1 text-gray-700 dark:text-gray-200">Deadline</label>
            <input type="date" v-model="deadline" required
                   class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary dark:bg-gray-700 dark:border-gray-600 dark:text-gray-200" />
          </div>

          <!-- Assign To -->
          <div>
            <label class="block mb-1 text-gray-700 dark:text-gray-200">Assign To</label>
            <select v-model="assignedMember" required
                    class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary dark:bg-gray-700 dark:border-gray-600 dark:text-gray-200">
              <option disabled value="">Select a member</option>
              <option v-for="member in members" :key="member.id" :value="member.id">
                {{ member.handle }}
              </option>
            </select>
          </div>

          <!-- Buttons -->
          <div class="flex justify-end space-x-2">
            <button type="button" @click="$emit('close')"
                    class="flex items-center px-4 py-2 bg-gray-300 dark:bg-gray-600 text-gray-800 dark:text-gray-200 rounded-lg hover:bg-gray-400 dark:hover:bg-gray-500 transition">
              <Icon name="xMark" variant="outline" className="h-5 w-5 mr-2" />
              Cancel
            </button>
            <button type="submit"
                    class="flex items-center px-4 py-2 bg-primary text-white rounded-lg hover:bg-secondary transition">
              <Icon name="checkCircle" variant="solid" className="h-5 w-5 mr-2" />
              Add Task
            </button>
          </div>
        </form>
      </div>
    </div>
  </transition>
</template>

<script>
import Icon from '@/components/Icons/Icon.vue'; // Reusable Icon component

export default {
  name: 'AddTaskModal',
  components: {
    Icon
  },
  props: {
    project: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      title: '',
      description: '',
      deadline: '',
      assignedMember: '',
    }
  },
  computed: {
    members() {
      return this.project.members || []; // Assuming project has a members array
    }
  },
  methods: {
    submitTask() {
      const newTask = {
        title: this.title,
        description: this.description,
        deadline: this.deadline,
        assignedToInfo: this.members.find(member => member.id === this.assignedMember),
        status: 'IN_PROGRESS' // Default status
      };
      this.$emit('task-added', newTask);
      this.$emit('close');
    }
  }
}
</script>

<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>
