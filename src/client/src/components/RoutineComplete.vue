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
</script>

<template>
<div class="w-full h-[535px] flex items-center justify-center" v-if="r_id == undefined">
    <p class="font-semibold text-xl text-gray-500">Choose a routine</p>
</div>
<div class="grid grid-rows-[1fr_50px] shadow bg-[#FFFFFF] border border-gray-300 rounded p-2 w-full h-[535px]" v-else>
    <div class="overflow-y-auto py-2">
        <div class="flex items-center gap-1 font-semibold text-gray-800 text-xl">
            <p>{{r_title}}</p>
            <p>#1523</p>
        </div>
        <div>
            <span class="text-gray-500">{{r_description}}</span>
        </div>
        <div class="flex flex-col">
            <p class="font-semibold text-lg">Exercises</p>
            <div class="flex items-center gap-1 pb-3" v-for="exercise in r_exercises">
                <input readonly type="number" class="_input_number" v-model="exercise.repetitions"/>
                <p>x {{exercise.name}}</p>
            </div>
        </div>
        <div class="">
            <p class="font-semibold text-lg">Type</p>
            <p class="text-gray-500">{{r_type}}</p>
        </div>
        <div>
            <p class="font-semibold text-lg">Style</p>
            <p class="text-gray-500">{{r_style}}</p>
        </div>
        <div>
            <p class="font-semibold text-lg">Units</p>
            <input readonly type="number" class="_input_number" v-model="r_units"/>
        </div>
    </div>
    <div class="flex items-center gap-1 border-t">
        <img class="w-[35px] h-[35px] rounded-md object-fit border-2 border-gray-300"
        src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/800px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg" />
        <p class="text-gray-500 text-md">Luis Alejandro · 09/07/01</p>
    </div>
</div>
</template>