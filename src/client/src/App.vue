<script setup lang="ts">
import { RouterView } from 'vue-router';
import Schedule from './components/Schedule.vue';
</script>

<template>
<div class="w-full h-screen font-main">
    
    <!-- <Schedule/> -->
    <RouterView :key="$route.fullPath"/>
</div>
</template>

