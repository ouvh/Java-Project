<template>
  <div :class="messageContainerClass">
    <!-- Sender Avatar -->
    <img :src="message.senderInfo.profileImage || defaultAvatar" alt="Sender Avatar" class="w-10 h-10 rounded-full border-2 border-secondary">
    
    <!-- Message Content -->
    <div class="flex flex-col">
      <!-- Sender Name and Timestamp -->
      <div class="flex items-center space-x-2">
        <span class="text-sm font-semibold text-gray-800 dark:text-gray-100">{{ message.senderInfo.handle }}</span>
        <span class="text-xs text-gray-500 dark:text-gray-400">{{ formattedTimestamp }}</span>
      </div>
      <!-- Message Text -->
      <div :class="messageBubbleClass">
        <p class="text-white dark:text-gray-200">{{ message.content }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import Icon from '@/components/Icons/Icon.vue'; // Reusable Icon component

export default {
  name: 'MessageItem',
  components: {
    Icon
  },
  props: {
    message: {
      type: Object,
      required: true
    },
    isOwnMessage: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      defaultAvatar: require('@/assets/images/default-avatar.png') // Ensure you have this image
    }
  },
  computed: {
    formattedTimestamp() {
      const options = { hour: '2-digit', minute: '2-digit' };
      return new Date(this.message.timestamp).toLocaleTimeString(undefined, options);
    },
    messageContainerClass() {
      return this.isOwnMessage
        ? 'flex justify-end space-x-4 p-2'
        : 'flex space-x-4 p-2';
    },
    messageBubbleClass() {
      return this.isOwnMessage
        ? 'bg-primary text-white px-4 py-2 rounded-lg max-w-xs'
        : 'bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-gray-200 px-4 py-2 rounded-lg max-w-xs';
    }
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
