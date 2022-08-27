<script setup lang="ts">
import Navbar from "./Navbar.vue";
const days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
</script>

<template>
<Navbar/>
<div class="flex flex-col items-center px-6 gap-4 pt-[80px]">
    <div class="px-2 py-4 gap-8 grid grid-rows-[50px_1fr] w-full h-[570px] border border-gray-400 rounded-lg">
        <!-- <div class="flex flex-col items-center justify-center">
            <p class="text-2xl">14:53 (Venezuela Time)</p>
        </div> -->
        <div class="w-full h-full grid grid-cols-[1fr_1fr_1fr_1fr_1fr_1fr_1fr]">
            <div v-for="day in days" class="flex flex-col items-center gap-4">
                <p class="font-semibold text-2xl text-gray-800 bg">{{day}}</p>
                <div class="w-full h-full grid grid-rows-[min-content_min-content] gap-4">
                    <div class="flex flex-col items-center">
                        <p class="text-lg text-gray-500">18:30</p>
                        <p class="text-lg text-gray-500">Hell Week</p>
                        <!-- <p class="text-lg text-gray-500">Rest</p> -->
                    </div> 
                </div>
            </div>
        </div>

        
    </div>
</div>
</template>