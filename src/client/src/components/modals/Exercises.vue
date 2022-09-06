<script setup lang="ts">
import calisthenics from '../../exercises/calisthenics.json';
import weights from '../../exercises/weights.json';

const props = defineProps(["type"])
</script>


<template>
<div class="fixed bg-black/30 w-full h-full flex items-center justify-center z-50">
    <div class="flex flex-col items-center w-[550px] h-[550px] mt-[56px] bg-white card p-0">
        <div class="w-full flex justify-center items-center py-3 border-b rounded-t-lg bg-white">
            <h1 class="text-xl">{{type}} Exercises</h1>
        </div>
        <div class="flex flex-col w-full h-full gap-6 overflow-y-auto gap-2 py-2 px-8 bg-gray-100 divide-y-2">
            <div class="flex flex-col py-2" v-for="entry in calisthenics" v-if="props.type == 'Calisthenics'">
                <span class="text-lg font-semibold">{{entry.category}}</span>
                <span class="text-gray-600">{{entry.description}}</span>
                <div class="grid grid-cols-[auto_auto_auto] gap-x-4 gap-y-4 mt-4">
                    <span v-for="exercise in entry.exercises" @click="$emit('addExercise', {name: exercise.name, repetitions: 1})" class="flex items-center justify-center w-auto  clickable rounded px-1 py-2 bg-white shadow">{{ exercise.name }}</span>
                </div>
            </div>
            <div class="flex flex-col py-2" v-for="entry in weights" v-else>
                <span class="text-lg font-semibold">{{entry.category}}</span>
                <span class="text-gray-600">{{entry.description}}</span>
                <div class="grid grid-cols-[auto_auto_auto] gap-x-4 gap-y-4 mt-4">
                    <span v-for="exercise in entry.exercises" @click="$emit('addExercise', {name: exercise.name, repetitions: 1})" class="flex items-center justify-center w-auto  clickable rounded px-1 py-2 bg-white shadow">{{ exercise.name }}</span>
                </div>
            </div>
        </div>
        <div class="w-full flex items-center border-t border-gray-300 justify-center gap-4 p-2 clickable" @click="$emit('closeExercisesPage')">
            <button class="text-lg py-2">Go Back</button>
        </div>
    </div>
</div>

</template>