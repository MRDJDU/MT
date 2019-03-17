<template>
   <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">MT</div>
            <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" placeholder="username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="loginForm.password" @keyup.enter.native="submitForm('loginForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
 export default {
        data: function(){
            return {
                loginForm: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        { required: true, trigger: 'blur' }
                    ],
                    password: [
                        { required: true, trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // 保留this
                        let _this=this;
                        // 收集用户输入的账号和密码，发送给后端验证登陆
                        this.axios.post('http://localhost:8080/mt/manage/login',{
                            "name": _this.loginForm.username,
                            "password":_this.loginForm.password
                        })
                        .then(response => {
                            if(response.data.statuCode){
                                // 把数据存入本地存储
                                sessionStorage.setItem('manage_name',response.data.data.name);
                                sessionStorage.setItem('manage_id',response.data.data.manage_id);
                                sessionStorage.setItem('manage_grade',response.data.data.grade);
                                // localStorage.setItem('manage_name',response.data.data.name);
                                // localStorage.setItem('manage_id',response.data.data.manage_id);
                                // 把当前登录成功的用户存入vuex的store里面
                                _this.$store.commit('SAVE_USERINFO',response.data.data)
                                _this.$message({
                                    message: response.data.message,
                                    duration:4000,
                                    type: 'success'
                                });
                                _this.$router.push('/index');
                            }
                            else{
                                _this.$message.error({
                                    message:response.data.message,
                                    duration:4000
                                });
                            }
                        });
                       
                    } 
                    else {
                        this.$message.error({
                            message:'用户名和密码不能为空！',
                            duration:4000
                            });
                        return false;
                    }
                });
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .login-wrap{
        position: relative;
        width:100%;
        height:100%;
        background-image: url(../../assets/img/login.jpg) ;
        background-size: cover;
    }
    .ms-title{
        width:100%;
        line-height: 50px;
        text-align: center;
        font-size:20px;
        color: rgb(0, 0, 0);
        border-bottom: 1px solid rgba(0, 0, 0, 0.33);
        font-family:"Axure Handwriting";
    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:350px;
        margin:-190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255,255,255, 0.3);
        overflow: hidden;
    }
    .ms-content{
        padding: 30px 30px;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
        margin-bottom: 10px;
    }
    .login-tips{
        font-size:12px;
        line-height:30px;
        color:#fff;
    }
    .el-form-item__error{
        display: none;
    }
</style>
