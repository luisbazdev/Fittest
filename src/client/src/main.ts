import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

import vSelect from 'vue-select'
import 'vue-select/dist/vue-select.css';


import './index.css'

const pinia = createPinia()

const app = createApp(App)
app.component('v-select', vSelect)

app.use(pinia)
app.use(router)
app.mount('#app')
