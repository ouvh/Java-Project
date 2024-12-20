<template>
  <div class="flex flex-col items-center justify-center h-full">
    <!-- Animated Icon -->
    <Icon
      name="refresh"
      variant="solid"
      className="h-12 w-12 text-primary animate-spin"
      aria-label="Loading"
    />
    <!-- Loading Message -->
    <p class="mt-4 text-lg font-medium text-gray-700 dark:text-gray-300">Loading...</p>
  </div>
</template>

<script>
import Icon from '@/components/Icons/Icon.vue'; // Reusable Icon component

export default {
  name: 'Loading',
  components: {
    Icon
  }
};
</script>

<style scoped>
/* Optional: Customize the spin animation if needed */
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

.animate-spin {
  animation: spin 1s linear infinite;
}
</style>
