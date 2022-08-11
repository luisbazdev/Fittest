<script setup lang="ts">
import { RouterView } from 'vue-router';
import Sidebar from './Sidebar.vue';

</script>

<template>
<div class="w-full h-full grid grid-cols-[265px_1fr]">
    <Sidebar/>
    <p>routines</p>
</div>

</template>