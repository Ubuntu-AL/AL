<template>
    <div style="background-color: #d4d4d4;min-height:800px">
        <div style="width:100%;background-color: #545c64; overflow: hidden">
            <span class="demonstration" style="float:left;padding-top:10px;color:white;margin-left:1%">
                页头
            </span>
            <span class="demonstration" style="float:left;padding:5px;color:white;margin-left:2%;width:15%">
                <el-input
                        placeholder="请输入"
                        icon="search"
                        v-model="searchCriteria"
                        :on-icon-click="handleIconClick">
                </el-input>
            </span>
            <span class="demonstration" style="float:right;padding-top:10px;margin-right:1%">
                <el-dropdown>
                  <span class="el-dropdown-link" style="color:white">
                    {{userName}}<i class="el-icon-caret-bottom el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>个人信息</el-dropdown-item>
                    <el-dropdown-item>退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
            </span>
        </div>

        <div style="margin-top:5px">
            <el-row :gutter="10">
                <el-col :xs="4" :sm="4" :md="4" :lg="4">
                    <div>
                        <el-menu default-active="1"
                                 class="el-menu-vertical-demo"
                                 style="min-height:800px"
                                 @select="handleSelect"
                                 background-color="#545c64"
                                 text-color="#fff"
                                 active-text-color="#ffd04b">
                            <el-menu-item index="1"><i class="el-icon-menu"></i>用户管理</el-menu-item>
                            <el-menu-item index="2"><i class="el-icon-menu"></i>用户组管理</el-menu-item>
                            <el-menu-item index="3"><i class="el-icon-more"></i>工作管理</el-menu-item>
                        </el-menu>
                    </div>
                </el-col>
                <el-col :xs="20" :sm="20" :md="20" :lg="20">
                    <div>
                        <div style="border: 1px solid #A6A6A6; border-radius:6px; padding:5px; margin:2px; background-color: white">
                            <el-breadcrumb separator="/">
                                <el-breadcrumb-item v-for="item in breadcrumbItems">{{item}}</el-breadcrumb-item>
                            </el-breadcrumb>
                        </div>
                    </div>

                    <div style="margin-top:10px">
                        <router-view></router-view>
                    </div>
                </el-col>
            </el-row>
        </div>

        <div style="background-color: #e1e1e1;min-height:60px;line-height: 30px" align="center">
            <el-footer>
                <div>
                    <p style="color: gray">Designed by Lin.</p>
                </div>
            </el-footer>
        </div>
    </div>


</template>
<script type="text/ecmascript-6">
    export default {
        data(){
            return {
                searchCriteria: '',
                breadcrumbItems: ['首页'],
                userName: this.$store.state.MainPage.user.name,
            }
        },

        methods:{
            handleIconClick(ev) {
                console.log(ev);
            },

            handleSelect(key, keyPath){
                switch(key){
                    case '1':
                        this.$router.push('/page1');
                        this.breadcrumbItems  = ['人员管理']
                        break;
                    case '2':
                        this.$router.push('/page2')
                        this.breadcrumbItems  = ['用户组管理']
                        break;
                    case '3':
                        this.$router.push('/page3')
                        this.breadcrumbItems  = ['工作管理']
                        break;
                }
            },

        },
    }
</script>