<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20">
                    <div style="height:120px">
                        <div class="user-info">
                            <img :src="userImg" class="user-avator">
                            <div class="user-info-cont">
                                <div class="user-info-name">{{name}}</div>
                                <div>{{creatTime}}</div>
                                <div>{{role}}</div>
                            </div>
                        </div>
                        <div class="edit">
                                <el-row>
                                    <el-tooltip effect="dark" :content="fullscreen=` 修改信息 `" placement="left">
                                            <el-button icon="el-icon-edit" circle size='mini' @click="eidt"></el-button>
                                    </el-tooltip>
                                </el-row>
                        </div>
                    </div>
                </el-card>
                <el-card shadow="hover">
                    <div id="myChart" :style="{width: '350px', height: '290px'}"></div>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-lx-people grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">1234</div>
                                    <div>注册用户数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-lx-notice grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">321</div>
                                    <div>预约维修</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-lx-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">5000</div>
                                    <div>购物订单</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-col>
             <el-col :span="16">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>分享知识(未来扩展位)</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                     <el-table :data="todoList" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column width="250">
                            <template slot-scope="scope">
                                <div class="todo-item">2019/03/20 12:06:54  阅读量(1690)</div>
                            </template>
                        </el-table-column>
                        <el-table-column width="120">
                            <template slot-scope="scope">
                                <el-button @click="handleClick(scope.row)" type="text" size="small" icon="el-icon-edit" style="padding:0">编辑</el-button>
                                <el-button type="text" size="small" icon="el-icon-lx-read" style="padding: 0">查看</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="totalNum" :page-size="pageSize" :current-page.sync="currentPages"></el-pagination>
                    </div>
                </el-card>
            </el-col>
        </el-row>

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
        data:function() {
             var checkOldPassword = (rule, value, callback) => {
                if (!value) {
                return callback(new Error('密码不能为空'));
                }
                setTimeout(() => {
                    // 保留this
                    let _this=this;
                    this.axios.post('mt/manage/login',{
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
                userImg:require('../../../static/ManageImg/'+sessionStorage.getItem('manage_img')+'.png'),
                creatTime:sessionStorage.getItem('manage_creatTime'),
                totalNum : 20,// 总记录数
                pageSize : 8,// 一页显示记录数
                page:1,//记录当前页数
                currentPages:0,//设置当前页数
                name: sessionStorage.getItem('manage_name'),
                todoList: [{
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要写100行代码加几个bug吧',
                        status: false,
                    }, {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: true,
                    }
                ],
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
        computed: {
            role() {
                if(sessionStorage.getItem('manage_grade') == 0){
                    return '超级管理员';
                }
                else if(sessionStorage.getItem('manage_grade') == 1){
                     return '购物管理员'
                }
                else if(sessionStorage.getItem('manage_grade') == 3){
                     return '开发者'
                }
                else{
                    return '维修管理员'
                }
            }
        },
         mounted(){
            this.drawLine();
        },
        methods: {
           drawLine(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myChart'))
                // 绘制图表
                myChart.setOption({
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['新增用户数量','预约维修','购物订单']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['周一','周二','周三','周四','周五','周六','周日']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name:'新增用户数量',
                            type:'line',
                            itemStyle : {
								normal : {
                                    color:'rgb(45, 140, 240)',
									lineStyle:{
										color:'rgb(45, 140, 240)'
									}
								}
							},
                            data:[120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            name:'预约维修',
                            type:'line',
                            itemStyle : {
								normal : {
                                    color:'rgb(100, 213, 114)',
									lineStyle:{
										color:'rgb(100, 213, 114)'
									}
								}
							},
                            data:[150, 182, 191, 234, 290, 330, 310]
                        },
                        {
                            name:'购物订单',
                            type:'line',
                            itemStyle : {
								normal : {
                                    color:'rgb(242, 94, 67)',
									lineStyle:{
										color:'rgb(242, 94, 67)'
									}
								}
							},
                            data:[150, 232, 201, 154, 190, 330, 210]
                        }
                    ]
                });
            },


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
                this.axios.post('mt/manage/editMessage',{
                    "manage_id":sessionStorage.getItem('manage_id'),
                    "img":imgNew,
                    "password":passwordNew,
                    "name":usernameNew
                })
                .then(response => {
                    if(response.data.statuCode){
                        sessionStorage.setItem('manage_name',response.data.data.name);
                        sessionStorage.setItem('manage_img',response.data.data.img);
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
    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(100, 213, 114);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        float: left;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
        font-family:"Axure Handwriting";
    }
    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }
    .edit{
        float: right;
    }
    .mgb20 {
        margin-bottom: 20px;
    }
    .page{
        text-align: center;
    }
    .article{
        height:38px;
        /* border-bottom: 1px solid #ccc;  */
        display: flex;
        align-items: center;
        margin-bottom: 3px;
    }

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
