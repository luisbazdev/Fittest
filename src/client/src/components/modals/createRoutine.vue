<script setup lang="ts">
import type { Exercise } from '@/interfaces'
import { useUserStore } from '@/stores/UserStore'

import type { Ref } from 'vue'
import { ref} from 'vue'

import exerciseslist from '../../exercises/exercises.json';

const exercises: Ref<Array<Exercise>> = ref([])
const description: Ref<String> = ref("")
const units: Ref<Number> = ref(1)
const style: Ref<"Sets And Reps" | "Circuit" | "Every Minute On The Minute"> = ref("Sets And Reps")
const type: Ref<"Weights" | "Calisthenics" | "Weighted Calisthenics" | "Hybrid"> = ref("Weights")

</script>



<template>
<div class="flex flex-col items-center w-[500px] h-max bg-white card">
    <div class="w-full flex justify-center">
        <h1 class="">Create Routine</h1>
    </div>
    <div class="flex flex-col w-full gap-8">
        <div class="w-full">
            <p class="text-sm">Title</p>
            <input type="text text-white" class="w-full focus:outline-none border-b"/>
        </div>      
        <div>
            <p class="text-sm">Description</p>
            <input type="text" class="w-full focus:outline-none border-b"/>
        </div>     
    </div>
</div>
</template>