<template>
  <div class="max-w-md mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg">
    <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Sign Up</h2>
    <form @submit.prevent="handleSignup" class="space-y-4">
      
      <!-- Handle Input -->
      <div>
        <label class="block mb-1 font-medium text-gray-700">Handle</label>
        <input 
          type="text" 
          v-model="handle" 
          required
          placeholder="Enter your handle"
          class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
        />
      </div>
      
      <!-- Email Input -->
      <div>
        <label class="block mb-1 font-medium text-gray-700">Email</label>
        <input 
          type="email" 
          v-model="email" 
          required
          placeholder="Enter your email"
          class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
        />
      </div>
      
      <!-- Password Input -->
      <div>
        <label class="block mb-1 font-medium text-gray-700">Password</label>
        <input 
          type="password" 
          v-model="password" 
          required
          placeholder="Enter your password"
          class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
        />
      </div>
      
      <!-- Profile Image Input -->
      <div>
        <label class="block mb-1 font-medium text-gray-700">Profile Image</label>
        <input 
          type="file" 
          @change="handleFileUpload" 
          accept="image/*"
          class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
        />
        <!-- Image Preview -->
        <div v-if="previewImage" class="mt-4 flex flex-col items-center">
          <p class="text-gray-700 mb-2">Image Preview:</p>
          <img 
            :src="previewImage" 
            alt="Profile Image Preview" 
            class="w-32 h-32 object-cover rounded-full shadow-md"
          />
        </div>
      </div>
      
      <!-- Submit Button with Loading Indicator -->
      <button 
        type="submit" 
        :disabled="loading"
        class="w-full flex items-center justify-center px-4 py-2 bg-primary text-white rounded-lg hover:bg-secondary transition-colors disabled:opacity-50"
      >
        <svg 
          v-if="loading" 
          class="animate-spin h-5 w-5 mr-3 text-white" 
          xmlns="http://www.w3.org/2000/svg" 
          fill="none" 
          viewBox="0 0 24 24"
        >
          <circle 
            class="opacity-25" 
            cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4">
          </circle>
          <path 
            class="opacity-75" 
            fill="currentColor" 
            d="M4 12a8 8 0 018-8v8H4z">
          </path>
        </svg>
        <span>Sign Up</span>
      </button>
      
      <!-- Navigation to Login -->
      <p class="text-center text-gray-600">
        Already have an account? 
        <router-link to="/login" class="text-primary hover:underline">Login</router-link>
      </p>
      
    </form>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import Toastify from 'toastify-js';
import 'toastify-js/src/toastify.css';
import { storage } from '../../plugins/firebase'; // Ensure correct path
import { ref as storageRef, uploadBytesResumable, getDownloadURL } from "firebase/storage";

export default {
  data() {
    return {
      handle: '',
      email: '',
      password: '',
      profileImage: null,
      previewImage: null, // For image preview
      loading: false // To handle loading state
    }
  },
  methods: {
    ...mapActions('auth', ['register']),
    
    // Handle file selection and create a preview
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        this.profileImage = file;
        this.previewImage = URL.createObjectURL(file);
      } else {
        this.profileImage = null;
        this.previewImage = null;
      }
    },
    
    // Handle form submission
    async handleSignup() {
      this.loading = true;
      
      try {
        let profileImageUrl = '';
        
        // If a profile image is selected, upload it to Firebase Storage
       if (this.profileImage) {
      const fileName = `${this.handle}-${Date.now()}.${this.profileImage.name.split('.').pop()}`;
      const filePath = `profileImages/${fileName}`;
      const fileRef = storageRef(storage, filePath);

      const uploadTask = uploadBytesResumable(fileRef, this.profileImage);

      // Wrap the uploadTask in a Promise to await its completion
      profileImageUrl = await new Promise((resolve, reject) => {
        uploadTask.on('state_changed',
          (snapshot) => {
            // Optional: Monitor upload progress
            const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            this.uploadProgress = progress;
            console.log(`Upload is ${progress}% done`);
          },
          (error) => {
            // Handle unsuccessful uploads
            console.error("Upload error:", error);
            reject(error);
          },
          async () => {
            // Handle successful uploads on complete
            try {
              const downloadURL = await getDownloadURL(uploadTask.snapshot.ref);
              resolve(downloadURL);
            } catch (error) {
              console.error("Error getting download URL:", error);
              reject(error);
            }
          }
        );
      });
    }
        console.log(profileImageUrl)

        // Prepare the registration data
        const data = {
          handle: this.handle,
          email: this.email,
          password: this.password,
          profileImage: profileImageUrl // Include the image URL if uploaded
        };
        console.log("its here trust me")
        console.log(data)
        
        // Call the register action
        const response = await this.register(data);
        
        if(response === true){
          Toastify({
            text: "Account Registered Successfully",
            duration: 3000,
            close: true,
            gravity: "bottom", // `top` or `bottom`
            position: "right", // `left`, `center` or `right`
            backgroundColor: "green",
          }).showToast();
          
          // Redirect to login page after successful signup
          this.$router.push("/login");
        }
        else{
          Toastify({
            text: response,
            duration: 3000,
            close: true,
            gravity: "bottom",
            position: "right",
            backgroundColor: "red",
          }).showToast();
        }
        
      } catch (error) {
        console.error("Error during signup:", error);
        Toastify({
          text: "An error occurred during signup. Please try again.",
          duration: 3000,
          close: true,
          gravity: "bottom",
          position: "right",
          backgroundColor: "red",
        }).showToast();
      } finally {
        this.loading = false;
      }
    }
  }
}
</script>

<style scoped>
/* Custom Tailwind colors (if not already defined in your Tailwind config) */
.bg-primary {
  background-color: #1D4ED8; /* Blue-700 */
}
.bg-secondary {
  background-color: #2563EB; /* Blue-600 */
}
.focus\:ring-primary:focus {
  box-shadow: 0 0 0 2px rgba(29, 78, 216, 0.5); /* Blue-700 */
}
.text-primary {
  color: #1D4ED8; /* Blue-700 */
}
.hover\:bg-secondary:hover {
  background-color: #2563EB; /* Blue-600 */
}
</style>
