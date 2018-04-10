import Vue from 'vue'
import VueRouter from 'vue-router'
import page1 from '../components/supplementaryPage/subassemblyP1/supPage1.vue'
import page2 from '../components/supplementaryPage/subassemblyP2/supPage2.vue'
import page3 from '../components/supplementaryPage/subassemblyP3/supPage3.vue'
Vue.use(VueRouter)

const router = new VueRouter({
    routes:[{
        path: '/page1', component: page1
    },{
        path: '/page2', component: page2
    },{
        path:'/page3', component: page3
    }]
})

export default router;