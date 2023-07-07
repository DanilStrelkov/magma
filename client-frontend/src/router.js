import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/client',
            name: 'clientsStat',
            component: () => import('./components/ClientList.vue')
        },
        {
            path: '/client/:id',
            name: 'edit-client',
            component: () => import('./components/EditCustomer')
        },
        {
            path: '/add',
            name: 'add-client',
            component: () => import('./components/AddClient.vue')
        }
    ]
})