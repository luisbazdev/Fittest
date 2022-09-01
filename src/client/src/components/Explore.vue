<script setup lang="ts">
import Routine from './Routine.vue';
import { ref, type Ref } from 'vue'
import RoutineComplete from './RoutineComplete.vue'
import Navbar from './Navbar.vue'
import axios from 'axios';
import { useRouter } from 'vue-router';
import { RouterLink } from 'vue-router';

const router = useRouter()

const params = router.currentRoute.value.query

const q_style = ref(params.style || null)
const q_type = ref(params.type || null)
const q_page: Ref<Number> = ref(Number(params.page) || 0)

const page_first = ref(null)
const page_last = ref(null)

const routines: any = ref([])

if(q_type.value == null || q_type.value == ""){
    if(q_page.value == 0){
        axios.get(`http://localhost:8686/routine`, {
            withCredentials: true
        }).then((_routines: any) => {
            routines.value = _routines.data.content
            page_first.value = _routines.data.first
            page_last.value = _routines.data.last
        })
    }
    else{
        axios.get(`http://localhost:8686/routine?page=${q_page.value}`, {
            withCredentials: true
        }).then((_routines: any) => {
            routines.value = _routines.data.content
            page_first.value = _routines.data.first
            page_last.value = _routines.data.last
        })
    }

}

else{
    if(q_page.value == 0){
        axios.get(`http://localhost:8686/routine?type=${q_type.value}`, {
            withCredentials: true
        }).then((_routines: any) => {
            routines.value = _routines.data.content
            page_first.value = _routines.data.first
            page_last.value = _routines.data.last
        })
    }
    else{
        axios.get(`http://localhost:8686/routine?type=${q_type.value}&page=${q_page.value}`, {
            withCredentials: true
        }).then((_routines: any) => {
            routines.value = _routines.data.content
            page_first.value = _routines.data.first
            page_last.value = _routines.data.last
        })
    }
}

const styles = ["Sets And Reps", "Circuit", "Every Minute On The Minute"]
const types = ["Weights", "Calisthenics", "Weighted Calisthenics", "Hybrid"]
const units = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]
const exercises = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]

const preview: any = ref(null)

function setPreview(_preview: any){
    preview.value = _preview
}

function setStyle(s: any){
    q_style.value = s
}

function setType(t: any){
    q_type.value = t
}

function fetchRoutines(){
    if(q_type.value != null)
        router.push({path: '/explore', query: { type: q_type.value }})
    else
        router.push({path: '/explore'})
}

function goBack(){
    if(q_type.value != null)
        router.push({path: '/explore', query: { type: q_type.value, page: (q_page.value - 1) }})
    else
        router.push({path: '/explore', query: { page: q_page.value - 1}})
}

function goNext(){
    if(q_type.value != null)
        router.push({path: '/explore', query: { type: q_type.value, page: (q_page.value + 1) }})
    else
        router.push({path: '/explore', query: { page: (q_page.value + 1) }})
}

</script>

<template>
<Navbar/>
<div class="flex flex-col items-center px-6 gap-4 pt-[80px]">
    <div class="grid grid-cols-[815px_1fr] w-full h-[570px] border border-gray-400 rounded-lg">
        <div class="w-full h-full flex flex-col gap-8 overflow-y-auto p-6">
            <div class="w-full flex gap-3 justify-center items-end">
                <!-- <div>
                    <span class="label">Exercises</span>
                    <v-select :options="exercises" v-model="_exercises" class="w-[100px]" placeholder="All"></v-select>
                </div> -->
                <div>
                    <span class="label">Style</span>
                    <v-select :options="styles" v-model="q_style" @option:selected="setStyle" class="w-[290px]" placeholder="All"></v-select>
                </div>
                <div>
                    <span class="label">Type</span>
                    <v-select :options="types" v-model="q_type" @option:selected="setType" class="w-[250px]" placeholder="All"></v-select>
                </div>
                <div class="w-min flex gap-3 justify-center items-end">
                    <div>
                        <button class="button" @click="fetchRoutines">Search</button>
                    </div>
                </div>
                <!-- <div>
                    <span class="label">Units</span>
                    <v-select :options="units" v-model="unit" class="w-[100px]" placeholder="All"></v-select>
                </div> -->
            </div>

            <div class="w-full h-full grid grid-rows-[auto_40px]" v-if="routines.length > 0">
                <div class="w-full">
                    <div class="w-full">
                        <div class="w-full grid grid-cols-[auto_auto] justify-start gap-4">
                            <div class="w-full" v-for="routine in routines" >
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

                <div class="w-full flex items-center justify-center gap-8 py-16">
                    <button class="button" :class="{button_disabled: page_first == true}" :disabled="page_first == true" @click="goBack">Previous</button>
                    <button class="button" :class="{button_disabled: page_last == true}" :disabled="page_last == true" @click="goNext">Next</button>
                </div>
            </div>

            <div class="flex w-full h-full items-center justify-center" v-else>
                <p class="font-semibold text-2xl text-gray-500">No routines found!</p>
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
