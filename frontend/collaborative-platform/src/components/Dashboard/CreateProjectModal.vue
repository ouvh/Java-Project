<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white dark:bg-gray-800 p-6 rounded-lg w-96">
      <h2 class="text-2xl font-bold mb-4">Create New Project</h2>
      <form @submit.prevent="CreateProject" class="space-y-4">
        <div>
          <label class="block mb-1">Project Name </label>
          <input type="text" v-model="name" required
                 class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary" />
        </div>
        <div>
          <label class="block mb-1">Description</label>
          <textarea v-model="description" required
                    class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"></textarea>
        </div>
        <div class="flex justify-end space-x-2">
          <button type="button" @click="$emit('close')"
                  class="px-4 py-2 bg-gray-300 rounded-lg hover:bg-gray-400 transition">
            Cancel
          </button>
          <button type="submit"
                  class="px-4 py-2 bg-primary text-white rounded-lg hover:bg-secondary transition">
            Create
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  data() {
    return {
      name: '',
      description: ''
    }
  },
  methods: {
    ...mapActions('projects', ['createProject']),
    async CreateProject() {
      await this.createProject({ type:"private", name: this.name, description: this.description ,adminIDD:this.$store.state.auth.user.id});
      this.$emit('close');
    }
  }
}
</script>

<style scoped>
/* Add any additional styles if necessary */
</style>
