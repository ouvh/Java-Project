<template>
  <div class="min-h-screen flex">
    <div class="w-1/2 flex items-center justify-center bg-gray-200 dark:bg-gray-900">
      <div class="space-y-4">
        <div class="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-lg">
          <h2 class="text-2xl font-bold mb-4">Welcome Back!</h2>
          <LoginForm />
        </div>
      </div>
    </div>
    <div class="w-1/2 flex items-center justify-center bg-primary text-white">
      <div class="space-y-4">
        <div class="bg-primary-dark p-6 rounded-lg shadow-lg">
          <h2 class="text-2xl font-bold mb-4">Join Our Platform</h2>
          <p class="mb-4">“Collaboration is the key to success.”</p>
          <img src="@/assets/images/goodphoto.webp" alt="Inspiration" class="mx-auto w-40 h-40">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LoginForm from '../components/Auth/LoginForm.vue';

export default {
  components: {
    LoginForm
  }
}
</script>

<style scoped>
/* Add any additional styles or animations */
</style>
