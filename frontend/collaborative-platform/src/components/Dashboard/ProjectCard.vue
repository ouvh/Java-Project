<template>
  <div @click="goToProject" 
       class="bg-white dark:bg-gray-800 rounded-xl shadow-lg overflow-hidden transform hover:scale-105 hover:shadow-2xl transition duration-300 cursor-pointer">
    <div class="p-6">
      <h2 class="text-xl font-semibold text-primary dark:text-secondary mb-2 truncate">{{ project.name }}</h2>
      <p class="text-gray-600 dark:text-gray-300 text-sm mb-4 truncate">{{ project.description }}</p>
      <div class="flex items-center justify-between">
        <div class="flex items-center space-x-2">
          <img :src="project.adminInfo.profileImage || defaultAvatar" alt="Owner Avatar" class="w-6 h-6 rounded-full border-2 border-secondary">
          <span class="text-gray-700 dark:text-gray-200 text-sm">{{ project.adminInfo.handle }}</span>
        </div>
        <div class="flex items-center space-x-1">
          <!-- Task Icon (Outline) -->
          <Icon name="clipboardList" variant="outline" className="h-4 w-4 text-secondary" />
          <span class="px-2 py-1 bg-secondary text-white text-xs rounded-full">{{ project.tasks.length }} Tasks</span>
        </div>
      </div>
    </div>
    <div class="bg-primary dark:bg-secondary h-2"></div>
  </div>
</template>

<script>
import Icon from '@/components/Icons/Icon.vue'; // Import the reusable Icon component

export default {
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
      defaultAvatar: require('@/assets/images/default-avatar.png') // Ensure you have a default avatar image
    }
  },
  methods: {
    goToProject() {
      this.$router.push(`/project/${this.project.id}`);
    }
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
