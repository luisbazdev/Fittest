<script setup lang="ts">
import Routine from './Routine.vue';
import { ref } from 'vue'
import RoutineComplete from './RoutineComplete.vue'
import Routine1 from './modals/Routine.vue'
import Navbar from './Navbar.vue'
import axios from 'axios';

const seeCreateRoutineModal = ref(false)

function seeCreateRoutineModalTrue(){
    seeCreateRoutineModal.value = true
}

function seeCreateRoutineModalFalse(){
    seeCreateRoutineModal.value = false
}

const routines: any = ref([])

axios.get("http://localhost:8686/routine/me", {
    withCredentials: true
}).then((_routines: any) => {
    routines.value = _routines.data
})

const preview: any = ref(null)

function setPreview(_preview: any){
    preview.value = _preview
}
</script>

<template>
<Navbar/>
<Routine1 v-if="seeCreateRoutineModal == true" @close-modal="seeCreateRoutineModalFalse"/>
<div class="flex flex-col items-center px-6 gap-4 pt-[80px]">
    <div class="grid grid-cols-[auto_1fr] w-full h-[570px] border border-gray-400 rounded-lg">
        <div class="w-full h-full flex flex-col gap-8 overflow-y-auto p-6">
            <div class="w-full flex gap-12 justify-center items-end">
                <div class="_button" @click="seeCreateRoutineModalTrue">
                    <span>Create Routine</span>
                </div>
                <div class="_button">
                    <span>Create Collection</span>
                </div>
            </div>

            <div class="section">
                <p class="text-2xl">My routines</p>
                <div class="grid grid-cols-[auto_auto] justify-start gap-4">
                    <div v-for="routine in routines">
                        <Routine 
                        :key="routine.id"
                        :r_id = "routine.id"
                        :r_userId = "routine.userId" 
                        :r_title="routine.title"
                        :r_description="routine.description"
                        :r_created-at="routine.createdAt"
                        :r_exercises="routine.exercises"
                        :r_style="routine.style" 
                        :r_type="routine.type"
                        :r_units="routine.units"
                        :r_shared="routine.shared"
                        @set-preview="setPreview"/>
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