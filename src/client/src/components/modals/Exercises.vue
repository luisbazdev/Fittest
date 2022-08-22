<script setup lang="ts">
import exercises from '../../exercises/exercises.json';
</script>

<template>
<div class="fixed bg-black/30 w-full h-full flex items-center justify-center z-50">
    <div class="flex flex-col items-center w-[500px] h-[550px] mt-[56px] bg-white card">
        <div class="w-full flex justify-center">
            <h1 class="text-xl">Choose Exercise</h1>
        </div>
        <div class="flex flex-col w-full gap-2 overflow-y-auto">
            <div class="flex flex-col" v-for="exercise in exercises">
                <span @click="$emit('addExercise', {name: exercise.name, repetitions: 1})">{{ exercise.name }}</span>
            </div>
        </div>
        <button @click="$emit('closeExercisesPage')">close</button>
    </div>
</div>

</template>