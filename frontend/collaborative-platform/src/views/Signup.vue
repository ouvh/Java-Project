<template>
  <div class="min-h-screen flex">
    <div class="w-1/2 flex items-center justify-center bg-gray-200 dark:bg-gray-900">
      <div class="space-y-4">
        <div class="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-lg">
          <h2 class="text-2xl font-bold mb-4">Create an Account</h2>
          <SignupForm />
        </div>
      </div>
    </div>
    <div class="w-1/2 flex items-center justify-center bg-primary text-white">
      <div class="space-y-4">
        <div class="bg-primary-dark p-6 rounded-lg shadow-lg">
          <h2 class="text-2xl font-bold mb-4">Get Started</h2>
          <p class="mb-4">“The journey of a thousand miles begins with one step.”</p>
          <img src="@/assets/images/start.png" alt="Start" class="mx-auto w-24 h-24">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SignupForm from '../components/Auth/SignupForm.vue';

export default {
  components: {
    SignupForm
  }
}
</script>

<style scoped>
/* Add any additional styles or animations */
</style>
