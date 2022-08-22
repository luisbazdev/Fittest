<script setup lang="ts">
import Routine from './Routine.vue';
import { ref } from 'vue'
import RoutineComplete from './RoutineComplete.vue'
import Navbar from './Navbar.vue'
import axios from 'axios';

const routines: any = ref([])

axios.get("http://localhost:8686/routine", {
    withCredentials: true
}).then((_routines: any) => {
    routines.value = _routines.data
})

const style = ref(null)
const type = ref(null)
const unit = ref(null)
const _exercises = ref(null)

const styles = ['Sets And Reps', 'Circuit', 'Every Minute On The Minute']
const types = ["Weights", "Calisthenics", "Weighted Calisthenics", "Hybrid"]
const units = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]
const exercises = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]

const preview: any = ref(null)

function setPreview(_preview: any){
    preview.value = _preview
}

</script>

<template>
<Navbar/>
<div class="flex flex-col items-center px-6 gap-4 pt-[80px]">
    <div class="grid grid-cols-[auto_1fr] w-full h-[570px] border border-gray-400 rounded-lg">
        <div class="w-full h-full flex flex-col gap-8 overflow-y-auto p-6">
            <div class="w-full flex gap-3 justify-center items-end">
                <!-- <div class="w-[600px] flex gap-3 justify-center items-end">
                    <div class="w-full">
                        <input class="box field bg-transparent" placeholder="Search for routines"/>
                    </div>
                    <div>
                        <button class="button">Search</button>
                    </div>
                </div> -->
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
            </div>

            <div v-for="(routine, type, index) in routines">
                <div class="section" v-if="routine.length > 0">
                    <p class="text-2xl">{{type}}</p>
                    <div class="grid grid-cols-[auto_auto] justify-start gap-4">
                        <div v-for="_routine in routine">
                            <Routine 
                            :key="_routine.id"
                            :r_id = "_routine.id"
                            :r_userId = "_routine.userId" 
                            :r_title="_routine.title"
                            :r_description="_routine.description"
                            :r_created-at="_routine.createdAt"
                            :r_exercises="_routine.exercises"
                            :r_style="_routine.style" 
                            :r_type="_routine.type"
                            :r_units="_routine.units"
                            :r_shared="_routine.shared"
                            @set-preview="setPreview"/>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="w-full p-4 border-l border-gray-400">
            <RoutineComplete v-bind="preview"/>
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
