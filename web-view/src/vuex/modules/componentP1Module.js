/*
 * 用户基本信息
 */
const state = {
    //查询用户基本信息的表单
    groupForm: {
        id: '',
        name: '',
        group: '',
    },

    //是否进行查询
    queryFlag: false,

}

const actions = {
    //存入搜索船舶基本资料form值
    changeFormAction({commit}, payload) {
        commit('changeFormMutation', payload)
    },

    //更改是否搜索标识
    changeQueryFlagAction ({commit}, payload){
        commit('changeQueryFlagMutation', payload)
    },

}

//mutations，真正用来修改state的方法集
const mutations = {
    changeFormMutation (state, payload) {
        state.groupForm = payload
    },

    changeQueryFlagMutation (state, payload) {
        state.queryFlag = payload
    },
}

const getter = {

}

const moduleGroup = {
    state: state,
    mutations: mutations,
    actions: actions,
    getter: getter
}

export default moduleGroup;