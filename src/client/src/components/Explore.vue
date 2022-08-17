<script setup lang="ts">
import { RouterView } from 'vue-router';
import Routine from './Routine.vue';

</script>

<template>
<div class="h-full flex flex-col items-center pt-8 gap-4 px-16">
    <div class="w-[600px] h-min flex gap-4 box">
        <input type="text" class="field w-full"/>
        <button class="button">Search</button>
    </div>

    <div class="w-[600px] grid grid-cols-[1fr_1fr_1fr] gap-4">
        <div>
            <p>Type</p>
            <input type="text" class="box field"/>
        </div>
        <div>
            <p>Style</p>
            <input type="text" class="box field"/>
        </div>
        <div>
            <p>Units</p>
            <input type="text" class="box field"/>
        </div>
    </div>

    <div class="w-full grid grid-cols-[1fr_1fr_1fr] justify-center gap-6 h-full">
        <Routine/>
        <Routine/>
        <Routine/>
    </div>
</div>

</template>