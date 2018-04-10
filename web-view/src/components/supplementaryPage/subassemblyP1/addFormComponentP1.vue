<template>
    <div style="border-radius:5px;">
    <div style="border:1px solid;background-color:#FFFFFF;box-shadow: 2px 2px 5px #888888;overflow: hidden;border-radius:5px;">
            <div style="background-color:#20A0FF;padding:5px;color:white;">
                用户资料查询
            </div>
            <br/>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户姓氏" prop="firstName">
                    <el-input style="width: 60%;" v-model="ruleForm.firstName"></el-input>
                </el-form-item>
                <el-form-item label="用户名字" prop="lastName">
                    <el-input style="width: 60%;" v-model="ruleForm.lastName"></el-input>
                </el-form-item>
                <el-form-item label="email" prop="lastName">
                    <el-input style="width: 60%;" v-model="ruleForm.email"></el-input>
                </el-form-item>
                <!--<el-form-item label="活动时间" required>-->
                    <!--<el-col :span="11">-->
                        <!--<el-form-item prop="date1">-->
                            <!--<el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>-->
                        <!--</el-form-item>-->
                    <!--</el-col>-->
                    <!--<el-col class="line" :span="2">-</el-col>-->
                    <!--<el-col :span="11">-->
                        <!--<el-form-item prop="date2">-->
                            <!--<el-time-picker type="fixed-time" placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>-->
                        <!--</el-form-item>-->
                    <!--</el-col>-->
                <!--</el-form-item>-->
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="ruleForm.sex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item align="center">
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script type="text/ecmascript-6">
    export default {
        data() {
            return {
                ruleForm: {
                    firstName: '',
                    lastName: '',
                    email: '',
                    date1: '',
                    date2: '',
                    sex: '男',
                },
                rules: {
                    firstName: [
                        { required: true, message: '请输入姓氏', trigger: 'blur' },
                        { min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
                    ],
                    lastName: [
                        { required: true, message: '请输入名字', trigger: 'blur' },
                        { min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change' }
                    ],
                    // date1: [
                    //     { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                    // ],
                    // date2: [
                    //     { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
                    // ],
                    sex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>
