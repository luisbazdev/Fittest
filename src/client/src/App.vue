<script setup lang="ts">
import { RouterView } from 'vue-router';
</script>

<template>
<div class="w-full h-screen font-main">
    <RouterView :key="$route.fullPath"/>
</div>
</template>

