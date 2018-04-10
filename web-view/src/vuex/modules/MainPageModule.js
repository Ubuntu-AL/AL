/*
* MainPage模块
* */
const state ={
    groupTypeList:[
        {
            groupId: 1,
            groupName: 'worker'
        },
        {
            groupId: 2,
            groupName: 'mananger'
        }],

    //用于记录登陆的用户
    user:{
        name:"default",
        email:"default",
        loginName:"default"
    }
}

const actions = {
    //获取交互数据，并声明处理数据的mutation方法名
    changeGroupTypeListAction ({commit}, payload){
        commit("changeGroupTypeListMutation",payload)
    },

    changeUserNameAction({commit}, payload){
        commit("changeUserNameMutation" ,payload)
    },
}

//真正用于处理state数据的方法集合
const mutations = {
    changeGroupTypeListMutation(state, payload){
        state.groupTypeList = payload
    },

    changeUserNameMutation(state, payload){
        state.userName = payload
    }
}

const getter = {

}

const moduleMainPage = {
    state : state,
    actions : actions,
    mutations: mutations,
    getter : getter
}
export default moduleMainPage;