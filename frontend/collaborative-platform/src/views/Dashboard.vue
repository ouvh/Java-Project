<template>
  <div>
    <Navbar @toggle-theme="toggleTheme" />
    <div class="container mx-auto px-4 py-8">
      <div class="flex justify-between items-center mb-6">
        <h1 class="text-3xl font-bold text-gray-800 dark:text-gray-100">Your Projects</h1>
        <button @click="showCreateModal = true"
                class="flex items-center px-4 py-2 bg-secondary text-white rounded-lg shadow hover:bg-secondary-dark transition">
          <Icon name="plus" variant="solid" className="h-5 w-5 mr-2" />
          Create Project
        </button>
      </div>
      <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        <ProjectCard v-for="project in projects" :key="project.id" :project="project" />
      </div>
    </div>
    <CreateProjectModal v-if="showCreateModal" @close="showCreateModal = false" />
  </div>
</template>

<script>
import Navbar from '../components/Layout/Navbar.vue';
import ProjectCard from '../components/Dashboard/ProjectCard.vue';
import CreateProjectModal from '../components/Dashboard/CreateProjectModal.vue';
import Icon from '@/components/Icons/Icon.vue'; // Import the reusable Icon component
import { mapState, mapActions } from 'vuex';

export default {
  components: {
    Navbar,
    ProjectCard,
    CreateProjectModal,
    Icon
  },
  data() {
    return {
      showCreateModal: false
    }
  },
  computed: {
    ...mapState('projects', ['projects'])
  },
  methods: {
    ...mapActions('projects', ['fetchProjects']),
    toggleTheme() {
      this.$emit('toggle-theme');
    }
  },
  mounted() {
        this.fetchProjects(this.$store.state.auth.user.id);

  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
