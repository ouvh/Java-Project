<template>
  <div>
    <Navbar />
    <div class="container mx-auto px-4 py-8">
      <!-- Loading State -->
      <Loading v-if="isLoading" />


      <!-- Error State -->
      <div v-else-if="hasError" class="flex flex-col items-center justify-center h-full">
        <Icon name="exclamationCircle" variant="solid" className="h-12 w-12 text-red-500" aria-hidden="true" />
        <p class="mt-4 text-lg font-medium text-red-500">Failed to load project details.</p>
        <button @click="fetchProject" class="mt-4 px-4 py-2 bg-primary text-white rounded-lg hover:bg-primary-dark transition">
          Retry
        </button>
      </div>

      <!-- Project Content -->
      <div v-else>
        <h1 class="text-3xl font-bold text-gray-800 dark:text-gray-100 mb-4">{{ project.name }}</h1>
        <p class="text-gray-600 dark:text-gray-300 mb-6">{{ project.description }}</p>
        
        <!-- Tabs Navigation -->
        <div class="border-b mb-4">
          <nav class="-mb-px flex space-x-8">
            <button @click="activeTab = 'tasks'" :class="tabClass('tasks')">
              <Icon name="clipboardList" variant="solid" className="h-5 w-5 mr-2" />
              Tasks
            </button>
            <button @click="activeTab = 'chat'" :class="tabClass('chat')">
              <Icon name="chatBubbleBottomCenter" variant="solid" className="h-5 w-5 mr-2" />
              Chat
            </button>
            <button @click="activeTab = 'files'" :class="tabClass('files')">
              <Icon name="folder" variant="solid" className="h-5 w-5 mr-2" />
              Files
            </button>
          </nav>
        </div>

        <!-- Active Tab Content -->
        <div>
          <component :is="currentTabComponent" :project="project" @task-added="handleTaskAdded" :currentUser="currentUser"  @message-sent="handleMessageSent" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../components/Layout/Navbar.vue';
import TasksTab from '../components/Project/TasksTab.vue';
import ChatTab from '../components/Project/ChatTab.vue';
import FilesTab from '../components/Project/FilesTab.vue';
import Icon from '@/components/Icons/Icon.vue';
import Loading from '@/components/Layout/Loading.vue'; // Import the Loading component
import axios from '@/plugins/axios';


export default {
  components: {
    Navbar,
    TasksTab,
    ChatTab,
    FilesTab,
    Icon,
    Loading
  },
  data() {
    return {
      activeTab: 'tasks',
      project: {},
      isLoading: true, // Loading state
      currentUser: {}
    }
  },
  computed: {
    currentTabComponent() {
      switch (this.activeTab) {
        case 'tasks':
          return 'TasksTab';
        case 'chat':
          return 'ChatTab';
        case 'files':
          return 'FilesTab';
        default:
          return 'TasksTab';
      }
    }
  },
  methods: {
    tabClass(tabName) {
      return `flex items-center px-3 py-2 -mb-px border-b-2 text-sm font-medium ${
        this.activeTab === tabName
          ? 'border-primary text-primary'
          : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300 dark:text-gray-300 dark:hover:text-gray-100'
      }`;
    },
    async fetchProject() {
      this.isLoading = true; // Start loading
      try {
        const response = await axios.get(`/api/projects/${this.$route.params.id}`);
        this.project = response.data;
      } catch (error) {
        console.error('Error fetching project:', error);
        // Optionally, handle the error (e.g., show a notification)
      } finally {
        this.isLoading = false; // End loading
      }
    },
    async handleTaskAdded(newTask) {
      this.project.tasks.push(newTask);

      try {
        axios.post('/api/tasks/create?projectId='+this.$route.params.id+"&memberId="+newTask.assignedMember.id, newTask,{
        headers: {
          'Content-Type': 'application/json', // Explicitly setting the content type to JSON
        }});
      } catch (error) {
        console.error('Error adding project:', error);
        // Optionally, handle the error (e.g., show a notification)
      } 


    },
    handleMessageSent(newMessage) {
      const added = {
        content: newMessage,
        senderInfo:{id:this.$store.state.auth.user.id,profileImage:this.$store.state.auth.user.profileImage,handle:this.$store.state.auth.user.handle},
        timestamp: new Date()
      };
      this.project.chatGroup.messages.push(added);
      try {
        axios.post('/api/chats/message?chatGroupId='+this.project.chatGroup.id+"&senderId="+this.$store.state.auth.user.id,{content:newMessage},{
        headers: {
          'Content-Type': 'application/json', // Explicitly setting the content type to JSON
        }});
      } catch (error) {
        console.error('Error adding project:', error);
        // Optionally, handle the error (e.g., show a notification)
      } 
      
    }
  },
  mounted() {
    this.fetchProject();
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
