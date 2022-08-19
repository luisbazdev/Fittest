<script setup lang="ts">
import type { Exercise } from '@/interfaces'
import { useUserStore } from '@/stores/UserStore'

import axios from 'axios'

import { type Ref } from 'vue'
import { ref, reactive } from 'vue'
import Exercises from './Exercises.vue'

interface IExercise{
    name: String,
    repetitions: Number
}
// open exercises modal
const selectExercises = ref(false)
// selected exercises array
const exercises: any = reactive([])

function openExercisesPage(){
    selectExercises.value = true
}

function closeExercisesPage(){
    selectExercises.value = false
}

function addExercise(exercise: any){
    exercises.push(exercise)
    selectExercises.value = false
}

function createRoutine(){
    let routine = {
        title: title.value,
        description: description.value,
        exercises: exercises,
        type: type.value,
        style: style.value,
        units: units.value,
        createdAt: new Date()
    }

    axios.post("http://localhost:8686/routine", {
        routine
    }, {
        withCredentials: true
    }).then((res) => {
        console.log(res)
        // if request is successful. . .
        if(res.data){
            console.log('routine created!')
        }
    })
}

const title = ref("")
const description = ref("")
const type = ref("Weights")
const style = ref("Sets Ands Reps")
const units = ref(1)

const types = ["Weights", "Calisthenics", "Weighted Calisthenics", "Hybrid"]
const styles = ['Sets And Reps', 'Circuit', 'Every Minute On The Minute']
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
                <input type="text" class="_input" v-model="title" placeholder="Hell Week"/>
            </div>    
            <div class="flex flex-col">
                <span class="title">Description</span>
                <input type="text" class="_input" v-model="description" placeholder="To remember those times..."/>
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
            <button class="border border-gray-400 rounded text-gray-500 py-2" @click="createRoutine">Create</button>
            <button class="border border-gray-400 rounded text-gray-500 py-2" @click="$emit('closeModal')">Cancel</button>
        </div>
    </div>
</div>

<Exercises v-else @close-exercises-page="closeExercisesPage" @add-exercise="addExercise"/>
</template>