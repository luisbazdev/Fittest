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
const emit = defineEmits(['setPreview'])
function setPreview(){
    emit('setPreview', routine)
}
</script>

<template>
<div class="routine" @click="setPreview">
<!-- <div class="shadow bg-[#FFFFFF] border border-gray-300 rounded p-2 w-[370px] h-auto grid grid-rows-[auto_auto_auto_auto]"> -->
    <div class="flex items-center gap-1">
        <p class="font-semibold text-xl">{{r_title}}</p>
        ·
        <p class="text-gray-500">#1523</p>
    </div>
    <div class="flex h-full items-center content-center">
        <p class="text-[#0F78A9] font-semibold">{{r_type}}</p>
    </div>
    <div class="">
        <span class="text-gray-500">{{r_description}}</span>
    </div>
    <div class="flex items-center gap-1">
        <img class="w-[35px] h-[35px] rounded-md object-fit border-2 border-gray-300"
        src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/800px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg" />
        <p class="text-gray-500 text-md">Luis Alejandro · 09/07/01</p>
    </div>

</div>
</template>