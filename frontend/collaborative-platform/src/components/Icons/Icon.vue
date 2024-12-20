<template>
  <component :is="currentIcon" v-bind="iconProps" />
</template>

<script>
import * as SolidIcons from '@heroicons/vue/solid';
import * as OutlineIcons from '@heroicons/vue/outline';

export default {
  name: 'Icon',
  props: {
    name: {
      type: String,
      required: true
    },
    variant: {
      type: String,
      default: 'outline', // 'solid' or 'outline'
    },
    className: {
      type: String,
      default: '',
    },
    ariaLabel: {
      type: String,
      default: '',
    }
  },
  computed: {
    currentIcon() {
      const icons = this.variant === 'solid' ? SolidIcons : OutlineIcons;
      // Convert camelCase to PascalCase if necessary
      const iconName = this.capitalize(this.name) + 'Icon';
      return icons[iconName] || null;
    },
    iconProps() {
      return {
        class: this.className,
        'aria-label': this.ariaLabel || this.name,
      };
    }
  },
  methods: {
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    }
  }
}
</script>

<style scoped>
/* Optional: Add any additional styles if needed */
</style>
