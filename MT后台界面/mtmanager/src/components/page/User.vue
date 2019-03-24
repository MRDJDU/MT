<template>
<div ref="father" style="width:100%;height:100%">
    <div class="add">
        <el-tooltip effect="dark" :content="fullscreen=`新增管理员`" placement="left">
            <el-button icon="el-icon-plus" circle @click="addManage = true"></el-button>
        </el-tooltip>
        <el-tooltip effect="dark" :content="fullscreen=`重置`" placement="top">
             <el-button icon="el-icon-refresh" circle @click="reset"></el-button>
        </el-tooltip>
        <el-tooltip effect="dark" :content="fullscreen=`查询管理员`" placement="right">
             <el-button icon="el-icon-search" circle @click="searchManage = true"></el-button>
        </el-tooltip>
    </div>
    <div class="users-card" ref="son">
        <el-row :gutter="12" class="cards">
            <template v-for="item in items">
                <el-col :span="6">
                    <div class="cardfather">
                        <el-card shadow="hover" class="card">
                            <!-- 用户头像 -->
                            <div class="user-img">
                                <input hidden :value="item.state" class="manage-state"/>
                                <img :src="item.img">
                            </div>
                            <div class="message">
                                <h3>{{item.name}}</h3>
                                <span>{{item.creatTime}}</span>
                            </div>
                            <div class="deleted">
                                <el-row>
                                    <el-tooltip effect="dark" :content="fullscreen=` 禁用 `" placement="left">
                                        <el-button type="warning" icon="el-icon-lx-lock" circle size='mini' @click = "lock($event)" :value="item.manage_id"></el-button>
                                    </el-tooltip>
                                    <el-tooltip effect="dark" :content="fullscreen=` 删除 `" placement="right">
                                        <el-button type="danger" icon="el-icon-delete" circle size='mini' @click = "deletes($event)" :value="item.manage_id"></el-button>
                                    </el-tooltip>
                                </el-row>
                            </div>
                        </el-card>
                    </div>
                </el-col>
            </template>            
        </el-row>
    </div>
    <div class="page">
        <el-pagination background layout="prev, pager, next" :total="totalNum" :page-size="pageSize" :current-page.sync="currentPages" @current-change="handleCurrentChange"></el-pagination>
    </div>

    <el-dialog title="新增管理员" :visible.sync="addManage" width="30%" :show-close="false" center>
        <el-form :model="addForm"  ref="addForm">
            <el-form-item prop="username">
                <el-input v-model="addForm.username" autocomplete="off" placeholder="username      默认密码：888888"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="addManageCancel">取 消</el-button>
            <el-button type="primary" @click="addSubmit('addForm')">确 定</el-button>
        </div>
    </el-dialog>

    <el-dialog title="查询管理员" :visible.sync="searchManage" width="30%" :show-close="false" center>
        <el-form :model="searchForm"  ref="searchForm">
            <div style="margin: 0px 0px 10px 0px;text-align: center;" >
                <el-checkbox-group v-model="seachCheckboxGroup" size="small" @change="changes"> 
                    <el-checkbox-button v-for="term in terms" :label="term" :key="term">{{term}}</el-checkbox-button>
                </el-checkbox-group>
            </div>
            <div id="manageNameSeach" hidden>
                <el-form-item>
                    <el-input v-model="searchForm.name" autocomplete="off"  placeholder="username"></el-input>
                </el-form-item>
            </div>
            <div id="manageStateSeach" style="text-align: center;" hidden>
                <el-switch
                    v-model="value1"
                    active-text="启用"
                    inactive-text="禁用">
                </el-switch>
            </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="searchManageCancel">取 消</el-button>
            <el-button type="primary" @click="searchSubmit('searchForm')" :disabled=seachSubmit>确 定</el-button>
        </div>
    </el-dialog>



</div>
</template>
<script>
    const searchOptions = ['管理员姓名', '管理员状态'];
    export default {
        data:function(){
            return {
                totalNum : 0,// 总记录数
                pageSize : 8,// 一页显示记录数
                page:1,//记录当前页数
                currentPages:0,//设置当前页数
                sort:"creatTime",// 排序标记
                dir:"DESC",// 倒序
                name:"DJDU", // 查询条件管理员姓名
                createTime:"2018.03.12",// 查询条件，新增时间
                items:{},// 查询结果
                addManage: false, //添加管理员对话框是否显示
                addForm: {},//添加管理员对话框内容
                searchManage: false,//查询管理员对话框是否显示
                searchForm: {},//查询管理员对话框内容
                seachCheckboxGroup: [],//查询条件选项
                terms: searchOptions,//多选框的值设定
                value1:true,// 搜索条件Switch 开关的值，管理员状态
                seachSubmit:true,//搜索对话框中提交按钮是否可用
                seachname:null,//搜索条件管理员姓名
                seachstate:null//搜索条件管理员状态
            }
        },
        //页面加载函数
        mounted(){
            this.getManager();
            this.recursion();
        },
        methods :{
            // 页面加载时获取管理员列表
            getManager(){
                        // 保留this
                        let _this=this;
                        this.axios.post('http://localhost:8080/mt/manage/findAllManage',{
                            "manageDto":{},
                            "myPagaRequest":{
                                "page":1,
                                "limit":this.pageSize,
                                "sort":this.sort,
                                "dir":this.dir
                            }
                        })
                        .then(response => {
                            if(response.data.statuCode){
                                this.items=response.data.data.content;
                                for(var i=0;i<this.items.length;i++){
                                    this.items[i].img=require('../../../static/ManageImg/'+this.items[i].img+'.png');
                                }
                                this.totalNum=response.data.data.totalElements;
                                this.currentPages=1;
                            }
                            else{
                                this.$notify.error({
                                    title: response.data.message,
                                    offset: 100,
                                    showClose: false
                                });
                                this.addManage = false;
                                _this.addForm.username=null;
                            }
                        });
            },
            // 递归函数，随时监控页面的高度和宽度的变化，对页面中的元素样式进行调整
            recursion(){
                // 调整面板的长宽
                this.$refs.son.style.width =window.getComputedStyle(this.$refs.father).width;
                this.$refs.son.style.height =(this.$refs.father.offsetHeight*0.8268156)+'px';
                // 调整用户卡片的长宽,如果卡片被禁用则改变样式card
                for(var i=0;i<document.getElementsByClassName("cardfather").length;i++){
                    document.getElementsByClassName("user-img")[i].style.marginLeft=(((document.getElementsByClassName("cardfather")[i].offsetWidth)-102)/2)+'px';
                    document.getElementsByClassName("user-img")[i].style.marginRight=(((document.getElementsByClassName("cardfather")[i].offsetWidth)-102)/2)+'px';
                    if(document.getElementsByClassName("manage-state")[i].value==0){
                        document.getElementsByClassName("cardfather")[i].style.filter="grayscale(100%)"; 
                    }
                    else{
                        document.getElementsByClassName("cardfather")[i].style.filter="grayscale(0%)"; 
                    }
                }
                 setTimeout(() => {
                    this.recursion()
                },10);
            },
            // 分页行点击触发时间，val是点击的页数
            handleCurrentChange(val) {
                // 保留this
                let _this=this;
                _this.page=val;
                this.axios.post('http://localhost:8080/mt/manage/findAllManage',{
                    "manageDto":{
                        'name':_this.seachname,
                        'state':_this.seachstate
                    },
                    "myPagaRequest":{
                        "page":val,
                        "limit":this.pageSize,
                        "sort":this.sort,
                        "dir":this.dir
                    }
                })
                .then(response => {
                    // console.log(response)
                    if(response.data.statuCode){
                        this.items=response.data.data.content;
                        this.totalNum=response.data.data.totalElements;
                        for(var i=0;i<this.items.length;i++){
                                    this.items[i].img=require('../../../static/ManageImg/'+this.items[i].img+'.png');
                        }
                    }
                    else{
                        this.$notify.error({
                            title: response.data.message,
                            offset: 100,
                            showClose: false
                        });
                        this.addManage = false;
                        _this.addForm.username=null;
                    }
                });
            },
            // 冻结管理员
            lock(e){
                let id = e.currentTarget.value;
                // 保留this
                let _this=this;
                this.axios.post('http://localhost:8080/mt/manage/state',{
                    "manage_id": id
                })
                .then(response => {
                    if(response.data.statuCode){
                            this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
                        });
                        console.log(_this.seachstate);
                        this.axios.post('http://localhost:8080/mt/manage/findAllManage',{
                            "manageDto":{
                                'name':_this.seachname,
                                'state':_this.seachstate
                            },
                            "myPagaRequest":{
                                "page":_this.page,
                                "limit":this.pageSize,
                                "sort":this.sort,
                                "dir":this.dir
                            }
                        })
                        .then(response => {
                            if(response.data.statuCode){
                                this.items=response.data.data.content;
                                this.totalNum=response.data.data.totalElements;
                                for(var i=0;i<this.items.length;i++){
                                    this.items[i].img=require('../../../static/ManageImg/'+this.items[i].img+'.png');
                                }
                            }
                            else{
                                this.$notify.error({
                                    title: response.data.message,
                                    offset: 100,
                                    showClose: false
                                });
                            }
                        });
                    }
                    else{
                        this.$notify.error({
                            title: response.data.message,
                            offset: 100,
                            showClose: false
                        });
                    }
                });
            },
            // 删除管理员
            deletes(e){
                let id = e.currentTarget.value;
                this.$confirm('此操作将永久删除该管理员, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                    }).then(() => {
                        // 保留this
                        let _this=this;
                        this.axios.post('http://localhost:8080/mt/manage/delete',{
                            "manage_id": id
                        })
                        .then(response => {
                            if(response.data.statuCode){
                                 this.$notify({
                                    title: response.data.message,
                                    type: 'success',
                                    offset: 100,
                                    showClose: false
                                });
                                this.axios.post('http://localhost:8080/mt/manage/findAllManage',{
                                    "manageDto":{
                                        'name':_this.seachname,
                                        'state':_this.seachstate
                                    },
                                    "myPagaRequest":{
                                        "page":_this.page,
                                        "limit":this.pageSize,
                                        "sort":this.sort,
                                        "dir":this.dir
                                    }
                                })
                                .then(response => {
                                    if(response.data.statuCode){
                                        this.items=response.data.data.content;
                                        this.totalNum=response.data.data.totalElements;
                                        for(var i=0;i<this.items.length;i++){
                                            this.items[i].img=require('../../../static/ManageImg/'+this.items[i].img+'.png');
                                        }
                                    }
                                    else{
                                        this.$notify.error({
                                            title: response.data.message,
                                            offset: 100,
                                            showClose: false
                                        });
                                    }
                                });
                            }
                            else{
                                this.$notify.error({
                                    title: response.data.message,
                                    offset: 100,
                                    showClose: false
                                });
                            }
                        });
                    })
            },
            // 新增管理员
            addSubmit(formName){
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
                                    type: 'success',
                                    offset: 100,
                                    showClose: false
                                });
                                this.addManage = false;
                                _this.addForm.username=null;
                                this.getManager();
                            }
                            else{
                                this.$notify.error({
                                    title: response.data.message,
                                    offset: 100,
                                    showClose: false
                                });
                                this.addManage = false;
                                _this.addForm.username=null;
                            }
                        });
                    } 
                    else {
                        this.addManage = false;
                        return false;
                    }
                });
            },
            // 取消添加对话框
            addManageCancel(){
                this.addForm.username=null;
                this.addManage=false;
            },
            // 改变搜索条件选项
            changes(){
                switch(this.seachCheckboxGroup.length){
                    case 1:
                        if(this.seachCheckboxGroup[0]=='管理员姓名'){
                            document.getElementById('manageStateSeach').setAttribute('hidden','true');
                            document.getElementById('manageNameSeach').removeAttribute('hidden');
                        }
                        else{
                            document.getElementById('manageNameSeach').setAttribute('hidden','true');
                            document.getElementById('manageStateSeach').removeAttribute('hidden');
                        }
                        this.seachSubmit=false;
                        break;
                    case 2:
                        document.getElementById('manageNameSeach').removeAttribute('hidden');
                        document.getElementById('manageStateSeach').removeAttribute('hidden');
                        this.seachSubmit=false;
                        break;
                    case 0:
                        document.getElementById('manageNameSeach').setAttribute('hidden','true');
                        document.getElementById('manageStateSeach').setAttribute('hidden','true');
                        this.seachSubmit=true;
                        break;
                }
            },
            // 提交搜索条件
            searchSubmit(formName){
                // 保留this
                let _this=this;
                 switch(this.seachCheckboxGroup.length){
                    case 1:
                        if(this.seachCheckboxGroup[0]=='管理员姓名'){
                            _this.seachname=_this.searchForm.name;
                            _this.seachstate=null;
                        }
                        else{
                            _this.seachstate=_this.value1;
                            _this.seachname=null;
                        }
                        break;
                    case 2:
                        _this.seachname=_this.searchForm.name;
                        _this.seachstate=_this.value1;
                        break;
                }
                this.axios.post('http://localhost:8080/mt/manage/findAllManage',{
                    "manageDto":{
                        'name':_this.seachname,
                        'state':_this.seachstate
                    },
                    "myPagaRequest":{
                        "page":1,
                        "limit":this.pageSize,
                        "sort":this.sort,
                        "dir":this.dir
                    }
                })
                .then(response => {
                    if(response.data.statuCode){
                        this.items=response.data.data.content;
                        this.totalNum=response.data.data.totalElements;
                        for(var i=0;i<this.items.length;i++){
                            this.items[i].img=require('../../../static/ManageImg/'+this.items[i].img+'.png');
                        }
                        this.currentPages=1;
                    }
                    else{
                        this.$notify.error({
                            title: response.data.message,
                            offset: 100,
                            showClose: false
                        });
                        this.addManage = false;
                        _this.addForm.username=null;
                    }
                });
                _this.searchForm.name=null;
                _this.value1=true;
                this.seachCheckboxGroup=[];
                document.getElementById('manageNameSeach').setAttribute('hidden','true');
                document.getElementById('manageStateSeach').setAttribute('hidden','true');
                this.searchManage=false;
            }
            ,
            // 取消搜索对话框
            searchManageCancel(){
                document.getElementById('manageNameSeach').setAttribute('hidden','true');
                document.getElementById('manageStateSeach').setAttribute('hidden','true');
                this.seachCheckboxGroup=[];
                this.searchForm.name=null;
                this.value1=true;
                this.seachSubmit=true;
                this.searchManage=false;
            },
            // 重置搜索条件
            reset(){
                let _this=this;
                _this.seachname=null;
                _this.seachstate=null;
                 this.axios.post('http://localhost:8080/mt/manage/findAllManage',{
                    "manageDto":{
                        'name':_this.seachname,
                        'state':_this.seachstate
                    },
                    "myPagaRequest":{
                        "page":1,
                        "limit":this.pageSize,
                        "sort":this.sort,
                        "dir":this.dir
                    }
                })
                .then(response => {
                    if(response.data.statuCode){
                        this.items=response.data.data.content;
                        this.totalNum=response.data.data.totalElements;
                        for(var i=0;i<this.items.length;i++){
                            this.items[i].img=require('../../../static/ManageImg/'+this.items[i].img+'.png');
                        }
                        this.currentPages=1;
                    }
                    else{
                        this.$notify.error({
                            title: response.data.message,
                            offset: 100,
                            showClose: false
                        });
                        this.addManage = false;
                        _this.addForm.username=null;
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
        padding-top: 3px;
        padding-bottom: 5px;
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