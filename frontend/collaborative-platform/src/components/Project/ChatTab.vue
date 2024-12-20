<template>
  <div class="flex flex-col h-full">
    <!-- Chat Header -->
    <div class="flex items-center justify-between p-4 bg-gray-100 dark:bg-gray-800 rounded-t-lg">
      <h2 class="text-2xl font-bold text-gray-800 dark:text-gray-100 flex items-center">
        <Icon name="chatBubbleBottomCenter" variant="solid" class="h-6 w-6 mr-2 text-primary" />
        Chat
      </h2>
      <!-- Optional: Add more controls like search or settings -->
    </div>
    
    <!-- Messages List -->
    <div
      class="flex-1 overflow-y-auto p-4 bg-gray-50 dark:bg-gray-600 pb-24" 
      ref="messagesContainer"
    >
      <div v-if="messages.length === 0" class="text-center text-gray-500 dark:text-gray-300">
        No messages yet. Start the conversation!
      </div>
      <MessageItem
        v-for="message in messages"
        :key="message.id"
        :message="message"
        :isOwnMessage="message.senderInfo.id === this.$store.state.auth.user.id"
      />
      <!-- Dummy div to scroll into view -->
      <div ref="scrollAnchor"></div>
    </div>
    
    <!-- Send Message Component -->
    <div class="fixed bottom-0 left-20 right-20 p-4 bg-gray-100 dark:bg-gray-800 shadow-inner">
      <SendMessage @send-message="handleSendMessage" />
    </div>
  </div>
</template>


<script>
import MessageItem from './MessageItem.vue';
import SendMessage from './SendMessage.vue';
import Icon from '@/components/Icons/Icon.vue';
import Loading from '@/components/Layout/Loading.vue'; // Reusable Loading component

export default {
  name: 'ChatTab',
  components: {
    MessageItem,
    SendMessage,
    Icon,
    Loading
  },
  props: {
    project: {
      type: Object,
      required: true
    },
    currentUser: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      messages: []
    }
  },
  methods: {
    handleSendMessage(messageText) {
      
      this.$emit('message-sent', messageText);
      this.scrollToBottom();
      this.scrollToBottom();
      // TODO: Send the message to the backend/server
    },
    watch: {
    messages() {
      this.$nextTick(() => {
        this.scrollToBottom();
      });
    }
  },
    loadMessages() {
      // Assuming project.messages contains the chat messages
      this.messages = this.project.chatGroup.messages.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));
      this.scrollToBottom();
    },
    scrollToBottom() {
      const container = this.$refs.messagesContainer;
      const anchor = this.$refs.scrollAnchor;
      if (anchor) {
        anchor.scrollIntoView({ behavior: 'smooth' });
      } else {
        // Fallback if scrollAnchor is not available
        container.scrollTop = container.scrollHeight;
      }
    }
  },
  mounted() {
    this.loadMessages();
     this.scrollToBottom();
  },
  watch: {
    project: {
      handler() {
        this.loadMessages();
      },
      deep: true
    }
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */

</style>
