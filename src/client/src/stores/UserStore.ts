import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            id: null,
            name: null,
            email: null,
            // picture: null
        }
    },
    getters: {
        isUserLoggedIn: (state) => state.id !== null 
    },
    actions: {
        logUserIn(id: any, name: any, email: any){
            this.id = id
            this.name = name
            this.email = email
            // this.picture = picture
        },
        logUserOut(){
            this.id = null
            this.name = null
            this.email = null
        }
    }
})