<script setup lang="ts">
import Routine from './Routine.vue';
import { ref, type Ref } from 'vue'
import RoutineComplete from './RoutineComplete.vue'
import Routine1 from './modals/Routine.vue'
import Navbar from './Navbar.vue'
import axios from 'axios';
import { useRouter } from 'vue-router';
import ConfirmDelete from './modals/ConfirmDelete.vue';

const seeCreateRoutineModal = ref(false)
const seeDeleteModal = ref(false)
const deleteRoutine = ref(null)

function seeCreateRoutineModalTrue(){
    seeCreateRoutineModal.value = true
}

function seeCreateRoutineModalFalse(){
    seeCreateRoutineModal.value = false
}

function seeDeleteRoutineModalTrue(routine){
    deleteRoutine.value = routine.r_id
    seeDeleteModal.value = true
}

function seeDeleteRoutineModalFalse(){
    deleteRoutine.value = null
    seeDeleteModal.value = false
}

const router = useRouter()
const params = router.currentRoute.value.query

const routines: any = ref([])

const q_page: Ref<Number> = ref(Number(params.page) || 0)

const page_first = ref(null)
const page_last = ref(null)

if(q_page.value == 0){
        axios.get(`http://localhost:8686/routine/me`, {
            withCredentials: true
        }).then((_routines: any) => {
            routines.value = _routines.data.content
            page_first.value = _routines.data.first
            page_last.value = _routines.data.last
        })
    }
else{
    axios.get(`http://localhost:8686/routine/me?page=${q_page.value}`, {
        withCredentials: true
    }).then((_routines: any) => {
        routines.value = _routines.data.content
        page_first.value = _routines.data.first
        page_last.value = _routines.data.last
    })
}

function goBack(){
    if(q_page.value == 1)
        router.push({path: '/me'})
    else
        router.push({path: '/me', query: { page: q_page.value - 1}})
}

function goNext(){
    router.push({path: '/me', query: { page: (q_page.value + 1) }})
}

const preview: any = ref(null)

function setPreview(_preview: any){
    preview.value = _preview
}
</script>

<template>
<Navbar/>
<Routine1 v-if="seeCreateRoutineModal == true" @close-modal="seeCreateRoutineModalFalse"/>
<ConfirmDelete :r_id="deleteRoutine" v-if="seeDeleteModal == true" @close-modal="seeDeleteRoutineModalFalse"/>
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
                        @see-delete="seeDeleteRoutineModalTrue"
                        @set-preview="setPreview"
                        />
                    </div>
                </div>
            </div>

            <div class="w-full flex items-center justify-center gap-8 py-16">
                <button class="button" :class="{button_disabled: page_first == true}" :disabled="page_first == true" @click="goBack">Previous</button>
                <button class="button" :class="{button_disabled: page_last == true}" :disabled="page_last == true" @click="goNext">Next</button>
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