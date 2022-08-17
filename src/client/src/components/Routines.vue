<script setup lang="ts">
import Routine from './Routine.vue';
import type { Exercise } from '@/interfaces'
import { useUserStore } from '@/stores/UserStore'

import type { Ref } from 'vue'
import { ref } from 'vue'

const style = ref(null)
const type = ref(null)
const unit = ref(null)
const _exercises = ref(null)

const styles = ['Sets And Reps', 'Circuit', 'Every Minute On The Minute']
const types = ["Weights", "Calisthenics", "Weighted Calisthenics", "Hybrid"]
const units = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]
const exercises = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]

</script>

<template>
<div class="h-full flex flex-col items-center pt-8 px-16 gap-4">
    <!-- search bar -->
    <div class="w-[600px] flex gap-3 justify-center items-end">
        <div class="w-full">
            <input class="box field bg-transparent" placeholder="Search for routines"/>
        </div>
        <div>
            <button class="button">Search</button>
        </div>
    </div>
    <!-- filtering -->
    <div class="w-full flex gap-3 justify-center items-end">
         <div>
            <span class="label">Exercises</span>
            <v-select :options="exercises" v-model="_exercises" class="w-[100px]" placeholder="All"></v-select>
        </div>
        <div>
            <span class="label">Style</span>
            <v-select :options="styles" v-model="style" class="w-[290px]" placeholder="All"></v-select>
        </div>
        <div>
            <span class="label">Type</span>
            <v-select :options="types" v-model="type" class="w-[250px]" placeholder="All"></v-select>
        </div>
        <div>
            <span class="label">Units</span>
            <v-select :options="units" v-model="unit" class="w-[100px]" placeholder="All"></v-select>
        </div>
        <!-- <div>
            <button class="button">Search</button>
        </div> -->
    </div>
    <!-- routines box -->
    <div class="w-full h-full grid grid-cols-[1fr_1fr_1fr] gap-4">
        <Routine/>
        <Routine/>
        <Routine/>
        <Routine/>
        <Routine/>
        <Routine/>
        <Routine/>
        <Routine/>
        <Routine/>
    </div>

</div>
</template>

<style>
.vs__search::placeholder,
.vs__dropdown-toggle{
    color: #8a8a8a;
}
</style>