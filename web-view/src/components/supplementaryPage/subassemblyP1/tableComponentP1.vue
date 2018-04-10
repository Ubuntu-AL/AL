<template>
    <div style="box-shadow: 2px 2px 5px #888888;border-radius:5px;">
        <div style="background-color:#888888;padding:5px;color:white;overflow:hidden;border-radius:5px 5px 0 0">
            <span class="demonstration" style="float:left;padding:5px">用户资料</span>
        </div>

        <div style="margin:1%">
            <el-table
                    :data="tableData"
                    border
                    height="445"
                    style="width: 100%"
                    :default-sort = "{prop: 'name', order: 'descending'}">

                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="name"
                        label="姓名"
                        align="center"
                        sortable>
                </el-table-column>

                <el-table-column
                        prop="user.id"
                        label="id"
                        align="center"
                        sortable>
                </el-table-column>

                <el-table-column
                        prop="userInfo.sex"
                        label="性别"
                        align="center"
                        sortable>
                </el-table-column>

                <el-table-column
                        prop="user.email"
                        label="邮箱"
                        align="center"
                        sortable>
                </el-table-column>

                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button
                                type="text"
                                icon="el-icon-tickets"
                                size="mini"
                                circle
                                @click="openMsgBox" >
                        </el-button>
                        <el-button
                                type="primary"
                                icon="el-icon-edit"
                                size="mini"
                                circle>
                        </el-button>
                    </template>
                </el-table-column>

                <!--<el-table-column-->
                        <!--prop="groupName"-->
                        <!--label="类型名"-->
                        <!--align="center"-->
                        <!--sortable>-->
                <!--</el-table-column>-->

                <!--<el-table-column-->
                        <!--prop="group"-->
                        <!--label="类型id"-->
                        <!--align="center"-->
                        <!--sortable>-->
                <!--</el-table-column>-->

            </el-table>
        </div>
        <div style="margin-bottom: 0px" align="center">
            <el-button type="danger" round size="mediu">删除</el-button>
            <el-button type="primary" round size="mediu">添加</el-button>
        </div>
    </div>
</template>

<script>
    import { mapActions } from 'vuex'
    import axios from 'axios'

    var catchData = []
    var defaultTable=[
            {
                user: {
                    id: 'offline',
                    name: 'offline',
                    email: 'offline'
                },
                userInfo:{
                    sex: 'offline'
                }
            }
    ]

    export default {
        props:['searchflag'],
        data () {
            return {
                //表格数据
                tableData : defaultTable,

                //详情页可见性
                detailDialogVisible: false,

                //被点击当前船舶信息
                nowShipInfo:'',
            }
        },

        methods: {
            openMsgBox (){
                var detials = {};
                const h = this.$createElement;
                this.$msgbox({
                    title: '用户详细信息',
                    message: h('p', null, [
                        h('span', null, 'name:'), h('i', {style: 'color: teal'}, detials.name), h('br'),
                        h('span', null, 'loginName:'), h('i', {style: 'color: teal'}, detials.loginName), h('br'),
                        h('span', null, 'password:'), h('i', {style: 'color: teal'}, detials.password), h('br'),
                        h('span', null, 'email:'), h('i', {style: 'color: teal'}, detials.email), h('br')
                    ]),
                    showCancelButton: true,
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    center: true,
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: 'action: ' + action
                        });
                    }
                })
            },

            fetchlist:function () {
                //获取用户信息
                axios.get("/organization/userList")
                    .then((response) => {
                        // console.log(response.data);
                        this.setWebSiteStatus(response.data)
                    })
            },
            // 根据website获取状态
            setWebSiteStatus:function (websites) {
                var usermsg = [];
                // console.log(websites);
                console.log(websites.length);
                for(let site of websites){
                    console.log(site.usermsg);
                    usermsg.push(site.usermsg);
                }
                this.tableData = usermsg;
                catchData = usermsg;
            },

            //加载表格ajax
            loadData(){
                var id = this.$store.state.componentP1.groupForm.id;
                var name = this.$store.state.componentP1.groupForm.name;
                var group = this.$store.state.componentP1.groupForm.group;
                var tabledata = [];

                console.log(id)
                console.log(name)
                console.log(group)

                if(id != '' || name != '' || group){
                    catchData.forEach((item) => {
                        if(item.user.id == id || item.name == name)
                            tabledata.push(item)
                    })
                    this.tableData = tabledata;
                }
                else{
                    this.fetchlist();
                }

                this.totalNum = this.tableData.length;
            },

            //显示数据变更响应
            handleSizeChange() {
                this.loadData();
            },

            ...mapActions({
                search: 'changeQueryFlagAction'
            }),
        },

        mounted () {
            this.loadData();
        },

        watch: {
            searchflag(newval,oldval){
                if(newval){
                    this.loadData();
                    this.search(false);
                }
            }
        }

    }
</script>

<style>

</style>