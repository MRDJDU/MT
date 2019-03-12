<template>
<div ref="father" style="width:100%;height:100%">
    <div class="add">
        <el-tooltip effect="dark" :content="fullscreen=`新增管理员`" placement="left">
            <i class="el-icon-lx-roundaddfill"  @click="dialogFormVisible = true"></i>
        </el-tooltip>
    </div>
    <div class="users-card" ref="son">
        <el-row :gutter="12" class="cards">
            <el-col :span="6" id="ooo">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

              <el-col :span="6">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

              <el-col :span="6">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

              <el-col :span="6">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

              <el-col :span="6">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

              <el-col :span="6">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

              <el-col :span="6">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

              <el-col :span="6">
                <div class="cardfather">
                    <el-card shadow="hover" class="card">
                        <!-- 用户头像 -->
                        <div class="user-img">
                            <img src="../../assets/ManageImg/DJDU.png">
                        </div>
                        <div class="message">
                            <h3>{{name}}</h3>
                            <span>{{createTime}}</span>
                        </div>
                        <div class="deleted">
                            <el-row>
                                <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                    <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock"></el-button>
                                </el-tooltip>
                                <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                    <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes"></el-button>
                                </el-tooltip>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>

            
        </el-row>
    </div>
    <div class="page">
        <el-pagination background layout="prev, pager, next" :total="totalNum" :page-size="pageSize" @current-change="handleCurrentChange"></el-pagination>
    </div>

    <el-dialog title="新增管理员" :visible.sync="dialogFormVisible" width="30%" center>
        <el-form :model="addForm"  ref="addForm">
            <el-form-item prop="username">
                <el-input v-model="addForm.username" autocomplete="off" placeholder="username      默认密码：888888"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="submit('addForm')">确 定</el-button>
        </div>
    </el-dialog>

</div>
</template>
<script>
    export default {
        data:function(){
            return {
                totalNum : 88,
                pageSize : 8,
                cur_page : 1,
                name:"DJDU",
                createTime:"2018.03.12",
                dialogFormVisible: false,
                addForm: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                }
            }
        },
        mounted(){
            this.getManager();
            this.recursion();
        },
        methods :{
            getManager(){
                
            },
            recursion(){
                this.$refs.son.style.width =window.getComputedStyle(this.$refs.father).width;
                this.$refs.son.style.height =(this.$refs.father.offsetHeight*0.8268156)+'px';
                for(var i=0;i<document.getElementsByClassName("cardfather").length;i++){
                    document.getElementsByClassName("user-img")[i].style.marginLeft=(((document.getElementsByClassName("cardfather")[i].offsetWidth)-102)/2)+'px';
                    document.getElementsByClassName("user-img")[i].style.marginRight=(((document.getElementsByClassName("cardfather")[i].offsetWidth)-102)/2)+'px';
                }
                 setTimeout(() => {
                    this.recursion()
                }, 10);
            },
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            getData(){
                alert(this.pageSize);
            },
            add(){
              alert("add");  
            },
            lock(){
                alert("lock");
            },
            deletes(){
              alert("delete");
            },
            submit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // 保留this
                        let _this=this;
                        // 收集用户输入的账号和密码，发送给后端验证登陆
                        this.axios.post('http://localhost:8080/mt/manage/addManage',{
                            "name": _this.addForm.username,
                            "password":"888888"
                        })
                        .then(response => {
                            if(response.data.statuCode){
                                 this.$notify({
                                    title: response.data.message,
                                    type: 'success'
                                });
                                this.dialogFormVisible = false;
                                _this.addForm.username=null;
                            }
                            else{
                                this.$notify.error({
                                    title: response.data.message
                                });
                                this.dialogFormVisible = false;
                                _this.addForm.username=null;
                            }
                        });
                    } 
                    else {
                        this.dialogFormVisible = false;
                        return false;
                    }
                });
            }
        }
    }
</script>
<style>
    .add{
        text-align: center;
        padding: 0px 0px 10px 0px;
    }
    .el-icon-lx-roundaddfill{
        font-size: 40px;
        color: rgb(81, 104, 182);
    }
    .users-card{
        display:table-cell;
        vertical-align:middle;
        text-align: center;
    }
    .cards{
        text-align: center;
    }
    .card{
        height: 200px;
        margin-bottom:20px;
    }
    .page{
        text-align: center;
    }
    .el-card__body{
        padding: 0px;
        text-align: center;
        
    }
    #ooo{
        filter:grayscale(100%); 
    }
    .user-img{
        width:100px;
        height:100px;
        border-bottom: 1px solid rgba(0, 0, 0, 0.33);
    }
    .user-img img{
        display: block;
        width:100px;
        height:100px;
        border-radius: 50%;
    }
    .message{
        height: 100%;
        width: 100%;
        padding: 10px 0px 0px 0px;
        text-align: center;
        font-family:"Axure Handwriting";
    }
    .message span{
        font-size: 10px;
    }
    .el-row{
        text-align: center;
    }
    .deleted{
        height: 100%;
        width: 100%;
        padding: 10px 0px 0px 0px;
    }
</style>