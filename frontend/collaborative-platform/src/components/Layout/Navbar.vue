<template>
  <nav class="flex justify-between items-center p-4 bg-primary text-white shadow-md">
    <div class="flex items-center space-x-4">
      <router-link to="/" class=" text-secondary text-lg font-bold hover:text-secondary-dark transition">
        Collaborative Platform
      </router-link>
      <router-link v-if="isAuthenticated" to="/dashboard" class=" text-secondary hover:text-secondary-dark transition">
        Dashboard
      </router-link>
    </div>
    <div class="flex items-center space-x-4">
      <!-- Dark/Light Mode Toggle -->
      <button @click="toggleTheme" class="focus:outline-none">
        <template v-if="isDark">
          <!-- Moon Icon (Solid) -->
          <Icon name="moon" variant="solid" className="h-6 w-6" />
        </template>
        <template v-else>
          <!-- Sun Icon (Outline) -->
          <Icon name="sun" variant="outline" className="h-6 w-6" />
        </template>
      </button>

      <!-- User Avatar and Dropdown -->
      <div v-if="isAuthenticated" class="relative">
        <button @click="toggleDropdown" class="focus:outline-none">
          <img :src="user.profileImage || defaultAvatar" alt="User Avatar" class="w-8 h-8 rounded-full border-2 border-secondary">
        </button>
        <div v-if="dropdownOpen" class="absolute right-0 mt-2 w-48 bg-white dark:bg-gray-800 rounded-md shadow-lg z-50">
          <button @click="Logout" class="w-full text-left px-4 py-2 text-gray-800 dark:text-gray-200 hover:bg-gray-100 dark:hover:bg-gray-700 transition flex items-center">
            <!-- Logout Icon (Outline) -->
            <Icon name="logout" variant="outline" className="h-5 w-5 mr-2" />
            Logout
          </button>
        </div>
      </div>

      <!-- Login and Signup Links for Guests -->
      <div v-else class="space-x-2">
        <router-link to="/login" class="px-3 py-2 bg-secondary text-white rounded-lg hover:bg-primary transition flex items-center">
          <Icon name="login" variant="outline" className="h-5 w-5 mr-2" />
          Login
        </router-link>
        <router-link to="/signup" class="px-3 py-2 bg-primary text-white rounded-lg hover:bg-secondary transition flex items-center">
          <Icon name="userAdd" variant="outline" className="h-5 w-5 mr-2" />
          Sign Up
        </router-link>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import Icon from '@/components/Icons/Icon.vue'; // Import the reusable Icon component

export default {
  components: {
    Icon
  },
  data() {
    return {
      dropdownOpen: false,
      defaultAvatar: require('@/assets/images/default-avatar.png') // Ensure you have a default avatar image
    }
  },
  computed: {
    ...mapGetters('auth', ['isAuthenticated', 'user',"state"]),
    isDark() {
      return document.documentElement.classList.contains('dark');
    }
  },
  methods: {
    ...mapActions('auth', ['logout']),
    toggleTheme() {
      this.$emit('toggle-theme');
    },
    toggleDropdown() {
      this.dropdownOpen = !this.dropdownOpen;
    },
    async Logout() {
      await this.logout();
      this.$router.push('/login');
    },
    handleClickOutside(event) {
      const dropdown = this.$el.querySelector('.relative');
      if (dropdown && !dropdown.contains(event.target)) {
        this.dropdownOpen = false;
      }
    }
  },
  mounted() {
    // Close dropdown when clicking outside
    document.addEventListener('click', this.handleClickOutside);
  },
  beforeUnmount() {
    document.removeEventListener('click', this.handleClickOutside);
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
