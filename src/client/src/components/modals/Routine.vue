<script setup lang="ts">
import type { Exercise } from '@/interfaces'
import { useUserStore } from '@/stores/UserStore'

import type { Ref } from 'vue'
import { ref } from 'vue'
import Exercises from './Exercises.vue'

// open exercises modal
const selectExercises = ref(false)
// selected exercises array
const exercises: Ref<Array<any>> = ref([])

function openExercisesPage(){
    selectExercises.value = true
}

function closeExercisesPage(){
    selectExercises.value = false
}

function addExercise(exercise: any){
    exercises.value.push(exercise)
    selectExercises.value = false
}

const type = ref("Weights")
const style = ref("Sets Ands Reps")
const units = ref(1)

const styles = ['Sets And Reps', 'Circuit', 'Every Minute On The Minute']
const types = ["Weights", "Calisthenics", "Weighted Calisthenics", "Hybrid"]
</script>

<template>
<div class="fixed bg-black/10 w-full h-full flex items-center justify-center" v-if="selectExercises == false">
    <div class="flex flex-col items-center w-[530px] h-[550px] mt-[56px] bg-white card">
        <div class="w-full flex justify-center">
            <h1 class="text-xl">Create Routine</h1>
        </div>
        <div class="flex flex-col w-full h-full gap-2 overflow-y-auto">
            <div class="flex flex-col">
                <span class="title">Title</span>
                <input type="text" class="_input" placeholder="Hell Week"/>
            </div>    
            <div class="flex flex-col">
                <span class="title">Description</span>
                <input type="text" class="_input" placeholder="To remember those times..."/>
            </div>         
            <div class="flex flex-col">
                <span class="title">Exercises</span>
                <div class="flex flex-col gap-3">
                    <div class="flex items-center gap-1" v-for="exercise in exercises">
                        <input min="1" max="100" type="number" class="_input_number" v-model="exercise.repetitions"/>
                        <p>x {{exercise.name}}</p>
                    </div>
                </div>
  
                <p class="text-[#8a8a8a]" @click="openExercisesPage">Add exercise</p>
            </div> 
            <div class="flex flex-col">
                <span class="title">Type</span>
                <v-select :options="types" v-model="type" class="w-full" placeholder="All"></v-select>
            </div>      
            <div class="flex flex-col">
                <span class="title">Style</span>
                <v-select :options="styles" v-model="style" class="w-full" placeholder="All"></v-select>
            </div>      
            <div class="flex flex-col">
                <span class="label">Units</span>
                <input min="1" max="100" type="number" class="_input_number" v-model="units"/>
            </div>     
        </div>
        <div class="w-full grid grid-cols-[1fr_1fr] gap-4">
            <button class="border border-gray-400 rounded text-gray-500 py-2">Create</button>
            <button class="border border-gray-400 rounded text-gray-500 py-2" @click="$emit('closeModal')">Cancel</button>
        </div>
    </div>
</div>

<Exercises v-else @close-exercises-page="closeExercisesPage" @add-exercise="addExercise"/>
</template>