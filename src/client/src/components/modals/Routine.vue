<script setup lang="ts">
import axios from 'axios'

import { ref, reactive } from 'vue'
import Exercises from './Exercises.vue'

const emit = defineEmits(['closeModal'])

interface IExercise{
    name: String,
    repetitions: Number
}
const selectCalisthenicsExercises = ref(false)
const selectWeightsExercises = ref(false)
// selected exercises array
let exercises: any = reactive([])

function openCalisthenicsExercisesPage(){
    selectCalisthenicsExercises.value = true
}

function openWeightsExercisesPage(){
    selectWeightsExercises.value = true
}

function closeExercisesPage(){
    selectCalisthenicsExercises.value = false
    selectWeightsExercises.value = false
}

function addExercise(exercise: any){
    exercises.push(exercise)

    selectCalisthenicsExercises.value = false
    selectWeightsExercises.value = false
}

function removeExercise(exercise: any){
    //...
    console.log(exercises)
}

function createRoutine(){
    axios.post("http://localhost:8686/routine", {
        title: title.value,
        description: description.value,
        createdAt: new Date(),
        exercises: exercises,
        type: type.value,
        style: style.value,
        units: units.value,
        shared: true
    }, {
        withCredentials: true
    }).then((res) => {
        console.log(res)
        // if request is successful. . .
        if(res.data){
            console.log('routine created!')
        }

        emit('closeModal')
    })
}

const title = ref("")
const description = ref("")
const type = ref("Weights")
const style = ref("Sets Ands Reps")
const units = ref(1)

const types = ["Weights", "Calisthenics", "Hybrid"]
const styles = ['Sets And Reps', 'Circuit', 'Every Minute On The Minute']
</script>

<template>
<div class="fixed bg-black/30 w-full h-full flex items-center justify-center z-50" v-if="selectCalisthenicsExercises == false && selectWeightsExercises == false">
    <div class="flex flex-col items-center w-[550px] h-[550px] mt-[56px] bg-white p-0 card">
        <div class="w-full flex justify-center items-center py-3 border-b rounded-t-lg bg-white">
            <h1 class="text-xl">Create Routine</h1>
        </div>
        <div class="flex flex-col w-full h-full gap-4 overflow-y-auto bg-gray-100 py-2 px-8">
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

                <div class="flex items-center justify-center gap-2 py-2 text-[#4a4a4a] border-b">
                    Choose 
                    <p class="text-[#8a8a8a] clickable underline" @click="openCalisthenicsExercisesPage">Calisthenics Exercises</p> 
                    or
                    <p class="text-[#8a8a8a] clickable underline" @click="openWeightsExercisesPage">Weights Exercises</p> 
                </div>
                <div class="flex flex-col mt-4 gap-3" v-if="exercises.length > 0">
                    <div class="flex items-center gap-1" v-for="exercise in exercises">
                        <input min="1" max="100" type="number" class="_input_number" v-model="exercise.repetitions"/>
                        x
                        <p>{{exercise.name}}</p>
                        <!-- <font-awesome-icon icon="fa-solid fa-trash" class="ml-2 text-gray-800" @click="removeExercise(exercise)"/> -->
                    </div>
                </div>
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
                <span class="title">Units</span>
                <input min="1" max="100" type="number" class="_input_number" v-model="units"/>
            </div>     
        </div>
        <div class="w-full grid grid-cols-[1fr_1fr] gap-4 p-4 bg-white border-t rounded-b-lg">
            <button class="border border-gray-400 rounded text-gray-500 py-2" :disabled="!title || !description || exercises.length < 1 || !type || !style || units < 1" @click="createRoutine">Create</button>
            <button class="border border-gray-400 rounded text-gray-500 py-2" @click="$emit('closeModal')">Cancel</button>
        </div>
    </div>
</div>

<Exercises type="Calisthenics" v-else-if="selectCalisthenicsExercises == true" @close-exercises-page="closeExercisesPage" @add-exercise="addExercise"/>
<Exercises type="Weights" v-else-if="selectWeightsExercises == true" @close-exercises-page="closeExercisesPage" @add-exercise="addExercise"/>
</template>
