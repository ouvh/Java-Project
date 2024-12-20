<template>
  <div class="flex items-start space-x-4 p-4 bg-white dark:bg-gray-700 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300">
    <!-- Task Icon -->
    <div class="flex-shrink-0 mt-1">
      <Icon name="clipboardList" variant="solid" className="h-6 w-6 text-primary" />
    </div>

    <!-- Task Details -->
    <div class="flex-1">
      <div class="flex justify-between items-center">
        <h3 class="text-lg font-semibold text-gray-800 dark:text-gray-100">{{ task.title }}</h3>
        <span :class="deadlineClass">{{ formattedDeadline }}</span>
      </div>
      <p class="text-gray-600 dark:text-gray-300 mt-1">{{ task.description }}</p>
      <div class="flex items-center mt-3 space-x-2">
        <!-- Assigned Member Avatar -->
        <img :src="task.assignedToInfo.profileImage || defaultAvatar" alt="Assigned Member" class="w-8 h-8 rounded-full border-2 border-secondary">
        <span class="text-sm text-gray-700 dark:text-gray-200">{{ task.assignedToInfo.handle }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import Icon from '@/components/Icons/Icon.vue'; // Reusable Icon component

export default {
  name: 'TaskItem',
  components: {
    Icon
  },
  props: {
    task: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      defaultAvatar: require('@/assets/images/default-avatar.png') // Ensure you have this image
    }
  },
  computed: {
    formattedDeadline() {
      const options = { year: 'numeric', month: 'short', day: 'numeric' };
      return new Date(this.task.deadline).toLocaleDateString(undefined, options);
    },
    deadlineClass() {
      const today = new Date();
      const deadline = new Date(this.task.deadline);
      const difference = deadline - today;
      const oneDay = 24 * 60 * 60 * 1000;

      if (difference < 0) {
        return 'text-red-500 font-semibold';
      } else if (Math.abs(difference) < oneDay) {
        return 'text-yellow-500 font-semibold';
      } else {
        return 'text-green-500 font-semibold';
      }
    }
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
