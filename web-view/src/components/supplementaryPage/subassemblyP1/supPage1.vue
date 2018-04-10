<template>
    <transition name="el-zoom-in-bottom">
        <div>
            <div style="border-radius:5px;">
                <Form></Form>
            </div>

            <br/>

            <div style="border:1px solid;margin-top:5px;background-color:#FFFFFF;border-radius:5px">
                <Table :searchflag="search"></Table>
            </div>

            <!--<br/>-->
            <!--<div style="border:1px solid;margin-top:5px;background-color:#FFFFFF;border-radius:5px">-->
                <!--<AddForm></AddForm>-->
            <!--</div>-->
        </div>
    </transition>
</template>

<script type="text/ecmascript-6">
    import Form from './formComponentP1.vue'
    import Table from './tableComponentP1.vue'
    import AddForm from './addFormComponentP1.vue'
    export default {
        data () {
            return {

            }
        },

        components: {
            Form: Form,
            Table: Table,
            AddForm: AddForm
        },

        computed: {
            search(){
                return this.$store.state.componentP1.queryFlag;
            }
        }
    }

</script>

<style>

</style>