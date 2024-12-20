<template>
  <form @submit.prevent="handleLogin" class="space-y-4">
    <div>
      <label class="block mb-1">Email</label>
      <input type="email" v-model="email" required
             class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary" />
    </div>
    <div>
      <label class="block mb-1">Password</label>
      <input type="password" v-model="password" required
             class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary" />
    </div>
    <button type="submit" 
            class="w-full py-2 bg-primary text-white rounded-lg hover:bg-secondary transition">
      Login
    </button>
    <p class="text-center">
      Don't have an account? <router-link to="/signup" class="text-primary hover:underline">Sign Up</router-link>
    </p>
  </form>
</template>

<script>
import { mapActions } from 'vuex';
import Toastify from 'toastify-js';
import 'toastify-js/src/toastify.css';

export default {
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    ...mapActions('auth', ['login']),
    async handleLogin() {
      const response = await this.login({ email: this.email, password: this.password });
      if (response === 1){
        Toastify({
        text: "Welcome Again !",
        duration: 3000,
        close: true,
        gravity: "bottom", // `top` or `bottom`
        position: "right", // `left`, `center` or `right`
        backgroundColor: "green",
      }).showToast();
     this.$router.push('/dashboard');

      }else{
        Toastify({
        text: "Email or Password Incorrect",
        duration: 3000,
        close: true,
        gravity: "bottom", // `top` or `bottom`
        position: "right", // `left`, `center` or `right`
        backgroundColor: "red",
      }).showToast();
      }
    }
  }
} 
</script>

<style scoped>
/* Add any additional styles if necessary */
</style>
