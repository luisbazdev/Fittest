import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

import vSelect from 'vue-select'
import 'vue-select/dist/vue-select.css';
import './index.css'

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import { faEarthAmericas, faDumbbell, faEllipsisVertical } from '@fortawesome/free-solid-svg-icons'
import { faBookmark } from '@fortawesome/free-regular-svg-icons'

/* add icons to the library */
library.add(faEarthAmericas, faDumbbell, faBookmark, faEllipsisVertical)

const pinia = createPinia()

const app = createApp(App)
app.component('v-select', vSelect)
app.component('font-awesome-icon', FontAwesomeIcon)
app.use(pinia)
app.use(router)
app.mount('#app')
