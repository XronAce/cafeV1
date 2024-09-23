
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import Manager from "./components/listers/Cards"
import Detail from "./components/listers/Detail"

import BeverageBeverageManager from "./components/listers/BeverageBeverageCards"
import BeverageBeverageDetail from "./components/listers/BeverageBeverageDetail"

import NotificationNotificationManager from "./components/listers/NotificationNotificationCards"
import NotificationNotificationDetail from "./components/listers/NotificationNotificationDetail"




export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '//',
                name: 'Manager',
                component: Manager
            },
            {
                path: '///:id',
                name: 'Detail',
                component: Detail
            },

            {
                path: '/beverages/beverages',
                name: 'BeverageBeverageManager',
                component: BeverageBeverageManager
            },
            {
                path: '/beverages/beverages/:id',
                name: 'BeverageBeverageDetail',
                component: BeverageBeverageDetail
            },

            {
                path: '/notifications/notifications',
                name: 'NotificationNotificationManager',
                component: NotificationNotificationManager
            },
            {
                path: '/notifications/notifications/:id',
                name: 'NotificationNotificationDetail',
                component: NotificationNotificationDetail
            },





    ]
})
