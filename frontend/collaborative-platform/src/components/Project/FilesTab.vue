<template>
  <div class="max-w-4xl mx-auto p-6 bg-white shadow-md rounded-lg">
    <h2 class="text-3xl font-bold text-gray-800 mb-6">Manage Your Files</h2>
    
    <!-- Upload Form -->
    <form @submit.prevent="uploadFile" class="flex flex-col sm:flex-row items-center mb-8">
      <label 
        class="flex flex-col items-center px-4 py-6 bg-white text-blue rounded-lg shadow-md tracking-wide uppercase border border-blue cursor-pointer hover:bg-blue-50"
      >
        <svg class="w-8 h-8" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
          <path d="M16.88 9.1l-4.34-4.34a2 2 0 00-2.83 0l-4.34 4.34a2 2 0 102.83 2.83L10 8.83V17a2 2 0 104 0V8.83l2.12 2.12a2 2 0 002.83-2.83z"/>
        </svg>
        <span class="mt-2 text-base leading-normal">Select a file</span>
        <input type="file" @change="handleFileChange" class="hidden" required />
      </label>
      <button 
        type="submit" 
        class="mt-4 sm:mt-0 sm:ml-4 px-6 py-3 bg-blue-600 text-white rounded-lg shadow-md hover:bg-blue-700 transition duration-300"
      >
        Upload
      </button>
    </form>
    
    <!-- Upload Progress -->
    <div v-if="uploading" class="mb-4">
      <div class="flex items-center justify-between mb-1">
        <span class="text-base font-medium text-blue-700">Uploading {{ selectedFile.name }}</span>
        <span class="text-sm font-medium text-blue-700">{{ uploadProgress }}%</span>
      </div>
      <div class="w-full bg-gray-200 rounded-full h-2.5">
        <div 
          class="bg-blue-600 h-2.5 rounded-full" 
          :style="{ width: `${uploadProgress}%` }"
        ></div>
      </div>
    </div>
    
    <!-- Uploaded Files List -->
    <div>
      <h3 class="text-2xl font-semibold text-gray-700 mb-4">Uploaded Files</h3>
      <div v-if="files.length === 0" class="text-gray-500">No files uploaded yet.</div>
      <ul class="space-y-4">
        <li 
          v-for="file in files" 
          :key="file.fileName" 
          class="flex items-center justify-between p-4 bg-gray-100 rounded-lg shadow-sm hover:bg-gray-200 transition duration-200"
        >
          <div class="flex items-center">
            <FileIcon :fileName="file.fileName" class="w-8 h-8 mr-4"/>
            <div>
              <a 
                :href="file.storageUrl" 
                target="_blank" 
                class="text-blue-600 font-medium hover:underline"
              >
                {{ file.fileName }}
              </a>
              <p class="text-sm text-gray-600"> • {{ formatDate(file.createdAt) }}</p>
            </div>
          </div>
          <button 
            @click="downloadFile(file.storageUrl)" 
            class="text-green-500 hover:text-green-700"
            title="Download"
          >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" 
                 viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" 
                    stroke-width="2" d="M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1M16 12l-4 4m0 0l-4-4m4 4V4" />
            </svg>
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { storage } from '../../plugins/firebase';
import { ref as storageRef, uploadBytes, getDownloadURL } from "firebase/storage";
import axios from '@/plugins/axios';
// FileIcon Component to display icons based on file type
const FileIcon = {
  props: ['fileName'],
  computed: {
    fileType() {
      const extension = this.fileName.split('.').pop().toLowerCase();
      const mimeTypes = {
        pdf: 'application/pdf',
        doc: 'application/msword',
        docx: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
        xls: 'application/vnd.ms-excel',
        xlsx: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
        ppt: 'application/vnd.ms-powerpoint',
        pptx: 'application/vnd.openxmlformats-officedocument.presentationml.presentation',
        jpg: 'image/jpeg',
        jpeg: 'image/jpeg',
        png: 'image/png',
        gif: 'image/gif',
        txt: 'text/plain',
        zip: 'application/zip',
        rar: 'application/x-rar-compressed',
        // Add more as needed
      };
      return mimeTypes[extension] || 'application/octet-stream';
    },
    iconClass() {
      switch(this.fileType) {
        case 'application/pdf':
          return 'text-red-500';
        case 'application/msword':
        case 'application/vnd.openxmlformats-officedocument.wordprocessingml.document':
          return 'text-blue-500';
        case 'application/vnd.ms-excel':
        case 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet':
          return 'text-green-500';
        case 'application/vnd.ms-powerpoint':
        case 'application/vnd.openxmlformats-officedocument.presentationml.presentation':
          return 'text-yellow-500';
        case 'image/jpeg':
        case 'image/png':
        case 'image/gif':
          return 'text-purple-500';
        case 'application/zip':
        case 'application/x-rar-compressed':
          return 'text-gray-500';
        default:
          return 'text-gray-400';
      }
    }
  },
  template: `
    <svg 
      xmlns="http://www.w3.org/2000/svg" 
      class="h-8 w-8" 
      fill="currentColor" 
      viewBox="0 0 20 20">
      <path 
        :class="iconClass"
        d="M4 4a2 2 0 00-2 2v8a2 2 0 002 2h12a2 2 0 002-2V8l-6-6H4z" />
    </svg>
  `
};

export default {
  components: {
    FileIcon
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
      selectedFile: null,
      files: [],
      uploading: false,
      uploadProgress: 0
    }
  },
  methods: {
    handleFileChange(event) {
      this.selectedFile = event.target.files[0];
    },
    async uploadFile() {
      if (!this.selectedFile) {
        // Optionally, notify the user to select a file
        return;
      }

      this.uploading = true;
      this.uploadProgress = 0;

      try {
        // Create a reference to the file in Firebase Storage
        const fileReference = storageRef(
          storage,
          `projects/${this.project.id}/${this.selectedFile.name}`
        );

        // Upload the file to Firebase Storage with progress tracking
        const uploadTask = uploadBytes(fileReference, this.selectedFile);

        // To track progress, switch to use `uploadTask.on`
        // However, since `uploadBytes` does not support progress, we'll use `uploadBytesResumable`
        const { uploadBytesResumable, getDownloadURL } = require("firebase/storage");
        const resumableUpload = uploadBytesResumable(fileReference, this.selectedFile);

        resumableUpload.on('state_changed', 
          (snapshot) => {
            const progress = Math.round((snapshot.bytesTransferred / snapshot.totalBytes) * 100);
            this.uploadProgress = progress;
          }, 
          (error) => {
            throw error;
          }, 
          async () => {
            // Retrieve the download URL of the uploaded file
            const url = await getDownloadURL(resumableUpload.snapshot.ref);

            // Save file information to your backend
            const response = await axios.post(
              `/api/files/projects/${this.project.id}?uploadedById=${this.$store.state.auth.user.id}`,
              {
                fileName: this.selectedFile.name,
                storageUrl: url,
                size: this.selectedFile.size, // Include file size
                createdAt: new Date().toISOString() // Include upload date
              },
              {
                headers: {
                  "Content-Type": "application/json",
                },
              }
            );

            // Update the local files array with the new file
            this.files.push({ 
              fileName: this.selectedFile.name, 
              storageUrl: url,
              size: this.selectedFile.size,
              createdAt: new Date().toISOString()
            });

            // Reset the selected file input and upload state
            this.selectedFile = null;
            this.uploading = false;
            this.uploadProgress = 0;

            // Optionally, notify the user of a successful upload
          }
        );

      } catch (error) {
        console.error("Error uploading file:", error);
        this.uploading = false;
        this.uploadProgress = 0;
        // Optionally, handle the error (e.g., show an error notification to the user)
      }
    },

    async loadFiles() {
      try {
        const response = await axios.get(`/api/files/projects/${this.project.id}`);
        this.files = response.data.sort((a, b) => new Date(b.createdAt) - new Date(a.uploadedAt));
      } catch (error) {
        console.error("Error loading files:", error);
      }
    },

    formatFileSize(bytes) {
      const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
      if (bytes === 0) return '0 Byte';
      const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
      return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
    },

    formatDate(dateString) {
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute:'2-digit' };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },

    downloadFile(url) {
      window.open(url, '_blank');
    }
  },
  mounted() {
    this.loadFiles();
  },
  watch: {
    project: {
      handler() {
        this.loadFiles();
      },
      deep: true
    }
  }
}
</script>

<style scoped>
/* Additional styles if necessary */
</style>
