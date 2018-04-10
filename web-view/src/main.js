import Vue from 'vue'
import ElementUI from 'element-ui'
import axios from 'axios'

import config from './properties/uri'
import 'element-ui/lib/theme-chalk/index.css'
import MainPage from './components/MainPage.vue'
import router from './router/myRouter'
import store from './vuex/myVuexConfig'

Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.$config = config
// Vue.use(VueSocketio, Vue.prototype.$config.socket);

axios.defaults.baseURL = Vue.prototype.$config.base;

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(MainPage)
})
