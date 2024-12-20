<template>
  <div>
    <Navbar />
    <HeroSection />
    <FeaturesSection />
  </div>
</template>

<script>
import Navbar from '../components/Layout/Navbar.vue';
import HeroSection from '../components/Home/HeroSection.vue';
import FeaturesSection from '../components/Home/FeaturesSection.vue';

export default {
  components: {
    Navbar,
    HeroSection,
    FeaturesSection
  }
}
</script>

<style scoped>
/* Add any additional styles if necessary */
</style>
