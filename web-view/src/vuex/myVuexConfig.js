import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import MainPage from './Modules/MainPageModule'
import componentP1 from './Modules/componentP1Module'

export default new Vuex.Store({
    modules: {
        MainPage: MainPage,
        componentP1: componentP1
    }
})