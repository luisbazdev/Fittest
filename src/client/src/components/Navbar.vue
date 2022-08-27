<script setup lang="ts">
import { RouterLink } from 'vue-router';
</script>

<template>
<div class="w-full h-[56px] bg-[#FFFFFF] grid grid-cols-[80px_1fr_80px] shadow fixed z-50">
    <div class="flex items-center justify-center">
        <p class="text-2xl font-semibold">Fittest</p>
    </div>
    <div class="flex justify-even items-center gap-20 pl-16 link">
        <div class="flex gap-2">
            <font-awesome-icon icon="fa-solid fa-earth-americas" size="lg" class=""/>
            <router-link to="explore" class="">Explore</router-link>
        </div>
        <div class="flex gap-2">
            <font-awesome-icon icon="fa-solid fa-dumbbell" size="lg"/>
            <router-link to="me">My Routines</router-link>   
        </div>
        <!-- <p>Schedule</p> -->
    </div>
    <div class="flex items-center justify-center">
        <img class="w-[40px] h-[40px] rounded-md object-fit border-2 border-gray-300"
        src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/800px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg" />
    </div>
</div>
</template>