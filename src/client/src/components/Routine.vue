<script setup lang="ts">
import type { Exercise } from '@/interfaces';
const routine = defineProps({
    r_id: String,
    r_userId: Number,
    r_title: String,
    r_description: String,
    r_createdAt: String,
    r_exercises: Array<Exercise>,
    r_style: String,
    r_type: String,
    r_units: Number,
    r_shared: Boolean
})

const date = new Date(routine.r_createdAt)

const emit = defineEmits(['setPreview'])
function setPreview(){
    emit('setPreview', routine)
}
</script>

<template>
<div class="routine" @click="setPreview">
    <div class="flex h-full items-center content-center mb-4 relative">
        <p class="font-semibold text-2xl text-gray-800">{{r_type}}</p>
        <font-awesome-icon class="absolute right-0 text-gray-700" icon="fa-solid fa-ellipsis-vertical" size="xl"/>
    </div>
    <div class="flex items-center gap-1 font-semibold text-gray-800">
        <p>{{r_title}}</p>
        <p>#1523</p>
    </div>
    <div class="border-b">
        <span class="text-gray-500">{{r_description}}</span>
    </div>
    <div class="flex items-start gap-1 pt-3">
        <img class="w-[35px] h-[35px] rounded-md object-fit border-2 border-gray-300"
        src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/800px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg" />
        <div class="flex flex-col justify-start">
            <p class="text-gray-500 text-md">Luis Alejandro</p>
            <p class="text-gray-500 text-sm">{{date.toLocaleTimeString()}} - {{date.toLocaleDateString()}}</p>
        </div>
    </div>
</div>
</template>
