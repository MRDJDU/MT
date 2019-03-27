<template>
    <div>
            <el-button icon="el-icon-search" @click="eidt" circle></el-button>
            <el-dialog title="修改信息" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="editManage" width="40%" :show-close="false" center>
                <div class="crop-demo">
                    <img :src="cropImg" class="pre-img">
                    <div class="crop-demo-btn">选择图片
                        <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage"/>
                    </div>
                </div>
                <div style="margin: 0px 0px 10px 0px;text-align: center;" >
                    <el-checkbox-group v-model="seachCheckboxGroup" size="small" @change="changes"> 
                        <el-checkbox-button v-for="term in terms" :label="term" :disabled="checkboxName" :key="term">{{term}}</el-checkbox-button>
                    </el-checkbox-group>
                </div>
                <el-form :model="editForm" status-icon :rules="rules" ref="editForm">
                     <div id="editOldPassword">
                        <el-col :span="24">
                                <el-form-item  prop="oldPassword">
                                    <el-input  type="password" v-model="editForm.oldPassword" autocomplete="off" placeholder="密码验证"></el-input>
                                </el-form-item>
                        </el-col>
                    </div>
                    <div id="editPassword" hidden>
                        <el-col :span="12">
                            <el-form-item  prop="newPassword">
                                <el-input type="password" v-model="editForm.newPassword" autocomplete="off" placeholder="新密码" :disabled="newnewPassword"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item  prop="newAgainPassword">
                                <el-input type="password" v-model="editForm.newAgainPassword" autocomplete="off" placeholder="重复密码" :disabled="newnewAgainPassword"></el-input>
                            </el-form-item>
                        </el-col>
                    </div>
                    <div id="editUsername" hidden>
                        <el-col>
                            <el-form-item prop="username">
                                <el-input v-model="editForm.username" autocomplete="off" placeholder="username"></el-input>
                            </el-form-item>
                        </el-col>
                    </div>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="editManageCancel('editForm')">取 消</el-button>
                    <el-button type="primary" @click="editSubmit('editForm')" :disabled=editdisable>确 定</el-button>
                </div>
            </el-dialog>
            <el-dialog title="裁剪图片" :show-close="false" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="dialogVisible" width="30%">
                <vue-cropper ref='cropper' :src="imgSrc" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage" style="width:100%;height:100%;":aspectRatio="1"></vue-cropper>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="cancelCrop">取 消</el-button>
                    <el-button type="primary" @click="submitEidt">确 定</el-button>
                </span>
            </el-dialog>
    </div>
</template>

<script>
    import VueCropper  from 'vue-cropperjs';
    const searchOptions = ['修改密码', '修改名字'];
    export default {
        data: function(){
            var checkOldPassword = (rule, value, callback) => {
                if (!value) {
                return callback(new Error('密码不能为空'));
                }
                setTimeout(() => {
                    // 保留this
                    let _this=this;
                    this.axios.post('http://localhost:8080/mt/manage/login',{
                        "name":sessionStorage.getItem('manage_name'),
                        "password":value
                    })
                    .then(response => {
                        if(!response.data.statuCode){
                            return callback(new Error('密码错误'));
                        }
                        else{
                            this.newnewPassword = false;
                            this.newnewAgainPassword = false;
                            this.checkboxName =false;
                            callback();
                            setTimeout(() =>{document.getElementById('editOldPassword').setAttribute('hidden','true');},1000);
                        }
                    });
                }, 500);
            };
            var checkNewPassword = (rule, value, callback) => {
                if (value === '') {
                callback(new Error('请输入新密码'));
                } else {
                if (this.editForm.newAgainPassword !== '') {
                    this.$refs.editForm.validateField('newAgainPassword');
                }
                callback();
                }
            };
            var checkNewAgainPassword = (rule, value, callback) => {
                if (value === '') {
                callback(new Error('请再次输入新密码'));
                } else if (value !== this.editForm.newPassword) {
                callback(new Error('两次输入密码不一致!'));
                } else {
                
                this.uppassword = true;
                switch(this.seachCheckboxGroup.length){
                    case 1:
                        if(this.seachCheckboxGroup[0]=='修改密码'){
                            if(this.uppassword){
                                this.editdisable = false;
                            }
                        }
                        else{
                            if(this.upusername){
                                this.editdisable = false;
                            }
                        }
                        break;
                    case 2:
                        if(this.upusername && this.uppassword){
                                this.editdisable = false;
                        }
                        break;
                    case 0:
                        this.editdisable = false;
                        break;
                }
                callback();
                }
            };
            var checkUsername = (rule, value, callback) => {
                if (!value) {
                return callback(new Error('名字不能为空'));
                }
                this.upusername = true;
                switch(this.seachCheckboxGroup.length){
                    case 1:
                        if(this.seachCheckboxGroup[0]=='修改密码'){
                            if(this.uppassword){
                                this.editdisable = false;
                            }
                        }
                        else{
                            if(this.upusername){
                                this.editdisable = false;
                            }
                        }
                        break;
                    case 2:
                        if(this.upusername && this.uppassword){
                                this.editdisable = false;
                        }
                        break;
                    case 0:
                        this.editdisable = false;
                        break;
                }
                callback();
            };
            return {
                defaultSrc: require('../../../static/ManageImg/'+sessionStorage.getItem('manage_img')+'.png'),//默认图片路径
                imgSrc: '',// 截取图片转化为的base64
                cropImg: '',// 显示图片的路径
                dialogVisible: false,// 截取图片的弹出框显示标志量
                editManage:false,// 修改用户信息的弹出框显示标志量
                editForm: {// 修改用户信息的弹出框from表单变量值
                    oldPassword:'',
                    newPassword:'',
                    newAgainPassword:'',
                    username:''
                },
                terms: searchOptions,// 修改条件多选框赋值
                seachCheckboxGroup:[],// 修改条件多选框选择的值
                editdisable:true, // 修改用户信息的弹出框信息提交确定按钮可用性
                upimg:false,
                uppassword:false,
                upusername:false,
                newnewPassword:true,
                newnewAgainPassword:true,
                checkboxName:true,
                rules: {// 修改条件规则
                    oldPassword: [
                        { validator:checkOldPassword, trigger: 'blur' }
                    ],
                    newPassword: [
                        { validator:checkNewPassword, trigger: 'blur' }
                    ],
                    newAgainPassword: [
                        { validator:checkNewAgainPassword, trigger: 'blur' }
                    ],
                    username: [
                        { validator:checkUsername, trigger: 'blur' }
                    ],
                }
            }
        },
        components: {
            VueCropper
        },
        created(){
            this.cropImg = this.defaultSrc;//页面创建时赋值默认路径
        },
        methods:{
            //点击修改信息按钮
            eidt(){
                this.editManage=true;
            },
            //点击选择图片按钮
            setImage(e){
                const file = e.target.files[0];
                if (!file.type.includes('image/')) {//判断是否为图片
                    return;
                }
                const reader = new FileReader();
                reader.onload = (event) => {
                    this.dialogVisible = true;
                    this.imgSrc = event.target.result;
                    this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
                };
                reader.readAsDataURL(file);
            },
            //展示截取后图片样式
            cropImage () {
                this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
            },
            //取消恢复原图片
            cancelCrop(){
                this.dialogVisible = false;
                this.cropImg = this.defaultSrc;
                this.upimg = false;
            },
            submitEidt(){
                this.dialogVisible = false;
                switch(this.seachCheckboxGroup.length){
                    case 1:
                        if(this.seachCheckboxGroup[0]=='修改密码'){
                            if(this.uppassword){
                                this.editdisable = false;
                            }
                        }
                        else{
                            if(this.upusername){
                                this.editdisable = false;
                            }
                        }
                        break;
                    case 2:
                        if(this.upusername && this.uppassword){
                                this.editdisable = false;
                        }
                        break;
                    case 0:
                        this.editdisable = false;
                        break;
                }
                this.upimg = true;
            },
            // 修改用户信息的弹出框取消点击按钮
            editManageCancel(formName){
                this.editdisable = true;
                this.editManage = false;
                this.upimg = false;
                this.$refs[formName].resetFields();
                this.newnewPassword = true;
                this.newnewAgainPassword = true;
                this.uppassword = false;
                this.upusername = false;
                this.seachCheckboxGroup = [];
                this.editForm.newAgainPassword = null;
                this.editForm.username = null;
                document.getElementById('editPassword').setAttribute('hidden','true');
                document.getElementById('editUsername').setAttribute('hidden','true');
                this.checkboxName = true;
                document.getElementById('editOldPassword').removeAttribute('hidden');
            },
            // 修改用户信息的弹出框提交按钮
            editSubmit(formName){
                let _this=this;
                if(_this.upimg){
                    imgNew = _this.cropImg;
                }
                switch(_this.seachCheckboxGroup.length){
                    case 1:
                        if(_this.seachCheckboxGroup[0]=='修改密码'){
                            if(_this.uppassword){
                                passwordNew = _this.editForm.newAgainPassword;
                                usernameNew = null;
                                _this.editForm.newAgainPassword = null;
                                _this.editForm.username = null;
                            }
                        }
                        else{
                            if(_this.upusername){
                                usernameNew = _this.editForm.username;
                                passwordNew = null;
                                _this.editForm.newAgainPassword = null;
                                _this.editForm.username = null;
                            }
                        }
                        break;
                    case 2:
                        if(_this.upusername && _this.uppassword){
                                passwordNew = _this.editForm.newAgainPassword;
                                usernameNew = _this.editForm.username;
                                _this.editForm.newAgainPassword = null;
                                _this.editForm.username = null;
                        }
                        break;
                    case 0:
                        passwordNew = null;
                        usernameNew = null;
                        _this.editForm.newAgainPassword = null;
                        _this.editForm.username = null;
                        break;
                }
                // 提交修改
                this.axios.post('http://localhost:8080/mt/manage/editMessage',{
                    "manage_id":sessionStorage.getItem('manage_id'),
                    "img":imgNew,
                    "password":passwordNew,
                    "name":usernameNew
                })
                .then(response => {
                    if(response.data.statuCode){
                        sessionStorage.setItem('manage_name',response.data.data.name);
                        this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
                        });
                        document.getElementById('usernamess').innerText=sessionStorage.getItem('manage_name');
                    }
                    else{
                         this.$notify.error({
                            title: response.data.message,
                            offset: 100,
                            showClose: false
                        });
                    }
                });
                this.editdisable = true;
                this.editManage = false;
                this.upimg = false;
                this.$refs[formName].resetFields();
                this.newnewPassword = true;
                this.newnewAgainPassword = true;
                this.uppassword = false;
                this.upusername = false;
                this.seachCheckboxGroup = [];
                document.getElementById('editPassword').setAttribute('hidden','true');
                document.getElementById('editUsername').setAttribute('hidden','true');
                this.checkboxName = true;
                document.getElementById('editOldPassword').removeAttribute('hidden');
                var imgNew = null;
                var passwordNew = null;
                var usernameNew = null;
            },
            // 多选框值变化
            changes(){
                switch(this.seachCheckboxGroup.length){
                    case 1:
                         if(this.seachCheckboxGroup[0]=='修改密码'){
                            document.getElementById('editUsername').setAttribute('hidden','true');
                            document.getElementById('editPassword').removeAttribute('hidden');
                            this.editdisable = true;
                            if(this.uppassword){
                                this.editdisable = false;
                            }
                        }
                        else{
                            document.getElementById('editPassword').setAttribute('hidden','true');
                            document.getElementById('editUsername').removeAttribute('hidden');
                            this.editdisable = true;
                            if(this.upusername){
                                this.editdisable = false;
                            }
                        }
                        break;
                    case 2:
                        document.getElementById('editPassword').removeAttribute('hidden');
                        document.getElementById('editUsername').removeAttribute('hidden');
                        this.editdisable = true;
                        if(this.uppassword && this.upusername){
                                this.editdisable = false;
                        }
                        break;
                    case 0:
                        document.getElementById('editPassword').setAttribute('hidden','true');
                        document.getElementById('editUsername').setAttribute('hidden','true');
                        if(this.upimg){
                            this.editdisable = false;
                        }
                        else{
                            this.editdisable = true;
                        }
                        break;
                }
            }
        }
    }
</script>

<style scoped>
    .crop-demo{
        text-align: center
    }
    .pre-img{   
        width: 150px;
        height: 150px;
        border-radius: 50%;
    }
    .crop-demo-btn{
        position: relative;
        width: 100px;
        height: 40px;
        line-height: 40px;
        padding: 0 20px;
        margin-left: 40%;
        margin-bottom: 5px;
        background-color: #409eff;
        color: #fff;
        font-size: 14px;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .crop-input{
        position: absolute;
        width: 100px;
        height: 40px;
        left: 0;
        top: 0;
        opacity: 0;
        cursor: pointer;
    }
</style>