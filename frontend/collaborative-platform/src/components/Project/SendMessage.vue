<template>
  <div class="flex items-center p-4 bg-gray-100 dark:bg-gray-800">
    <!-- Emoji Icon (Optional) -->
    <button class="focus:outline-none text-gray-500 dark:text-gray-300 mr-2">
      <Icon name="smile" variant="outline" className="h-6 w-6" />
    </button>
    
    <!-- Message Input -->
    <input
      type="text"
      v-model="messageText"
      @keyup.enter="sendMessage"
      placeholder="Type your message..."
      class="flex-1 px-4 py-2 bg-white dark:bg-gray-700 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary dark:text-gray-200"
    />
    
    <!-- Send Button -->
    <button @click="sendMessage" class="ml-2 focus:outline-none text-primary">
      <Icon name="paperAirplane" variant="solid" className="h-6 w-6" />
    </button>
  </div>
</template>

<script>
import Icon from '@/components/Icons/Icon.vue'; // Reusable Icon component

export default {
  name: 'SendMessage',
  components: {
    Icon
  },
  data() {
    return {
      messageText: ''
    }
  },
  methods: {
    sendMessage() {
      if (this.messageText.trim() === '') return;
      this.$emit('send-message', this.messageText.trim());
      this.messageText = '';
    }
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
