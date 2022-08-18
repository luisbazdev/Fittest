<script setup lang="ts">
import Routine from './Routine.vue';
import type { Exercise } from '@/interfaces'
import { useUserStore } from '@/stores/UserStore'

import type { Ref } from 'vue'
import { ref } from 'vue'
import RoutineComplete from './RoutineComplete.vue'

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
<div class="flex flex-col items-center px-6 gap-4 pt-[80px]">
    <div class="grid grid-cols-[auto_1fr] w-full h-[570px] border border-gray-400 rounded-lg">
        <div class="w-full h-full flex flex-col gap-8 overflow-y-scroll p-6">
            <div class="w-full flex gap-12 justify-center items-end">
                <div class="_button">
                    <span>Create Routine</span>
                </div>
                <div class="_button">
                    <span>Create Collection</span>
                </div>
            </div>

            <div class="section">
                <div class="grid grid-cols-[auto_auto] justify-start gap-4">
                    <Routine/>
                    <Routine/>
                    <Routine/>
                    <Routine/>
                    <Routine/>
                </div>
            </div>

            <!-- <div class="section">
                <p class="text-2xl">Leg Routines</p>
                <div class="grid grid-cols-[auto_auto] justify-start gap-4">
                    <Routine/>
                    <Routine/>
                </div>
            </div> -->
            
        </div>
        <div class="w-full p-4 border-l border-gray-400">
            <RoutineComplete/>
        </div>
    </div>
</div>
</template>

<style>
.vs__search::placeholder,
.vs__dropdown-toggle{
    color: #8a8a8a;
}
</style>