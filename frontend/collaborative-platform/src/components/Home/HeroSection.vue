<template>
<section class=" ttback min-h-screen flex items-center justify-center ">
  <section  class=" ttt  min-h-screen flex items-center justify-center  ">
    <div class="text-center">
      <h1 class=" text-white text-4xl md:text-6xl font-bold mb-4">Welcome to Your Collaborative Platform</h1>
      <p class=" text-white text-lg md:text-2xl mb-8">Manage projects, tasks, chat, and files seamlessly.</p>
      <router-link to="/signup" class="px-6 py-3 bg-primary text-white rounded-lg shadow-lg hover:bg-secondary transition">
        Get Started
      </router-link>
    </div>
  </section>
  </section>
</template>

<script>
export default {
  // Add any necessary logic
}
</script>

<style scoped>
.ttback {
  width: 100%;
  height: 100%;
  background-image: url("../../assets/images/back.webp");
  background-size: cover; /* Ensures the image covers the entire element */
  background-repeat: no-repeat; /* Prevents the image from repeating */
  background-position: center; /* Centers the image */
  z-index: 0;
  
  /* Apply blur effect */
 
}
.ttt{
  width: 100%;
  height: 100%;
  z-index: 1000;
      backdrop-filter: blur(7px);


}

</style>
