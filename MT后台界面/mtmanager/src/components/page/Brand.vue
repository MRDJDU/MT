<template>
     <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>
                <el-cascader expand-trigger="hover" :clearable = 'true' :options="options" class="handle-select mr10" v-model="selectedOptions"  placeholder="所属分类">
                </el-cascader>
                <el-input v-model="names" :clearable = 'true' placeholder="品牌名" class="handle-input mr10" ></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="success" icon="search" @click="add">新增</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="creatTime" label="创建日期" sortable width="250">
                </el-table-column>
                <el-table-column prop="category_name" label="所属分类" width="250">
                </el-table-column>
                <el-table-column prop="name" label="品牌名">
                </el-table-column>
                <el-table-column label="操作" width="300" align="center">
                    <template slot-scope="scope">
                        <el-button type="success" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button type="warning" @click="handleShow(scope.row)">{{scope.row.button}}</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="total" :page-size="limit" center>
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑品牌" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="所属分类">
                    <el-cascader expand-trigger="hover" :clearable = 'true' :options="options" class="handle-select mr10" v-model="form.category_id" placeholder="所属分类">
                </el-cascader>
                </el-form-item>
                <el-form-item label="品牌名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

         <!-- 新增弹出框 -->
        <el-dialog title="新增品牌" :visible.sync="addVisible" width="30%">
            <el-form ref="addform" :model="addform" label-width="70px">
                <el-form-item label="所属分类">
                    <el-cascader expand-trigger="hover" :clearable = 'true' :options="options" class="handle-select mr10" v-model="addform.category_id"  placeholder="所属分类">
                </el-cascader>
                </el-form-item>
                <el-form-item label="品牌名">
                    <el-input v-model="addform.name"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data:function(){
            return {
                page:1,
                limit:10,
                sort:"creatTime",
                dir:"DESC",
                total:1,
                names:'',
                tableData: [],
                multipleSelection: [],
                del_list: [],
                editVisible: false,
                delVisible: false,
                addVisible:false,
                form: {
                    name: '',
                    category_id:''
                },
                addform: {
                    name: '',
                    category_id:''
                },
                idx: -1,
                options:[
                ],
                selectedOptions:[],
                ids:[]
            }
        },
        mounted(){
            this.getCategory();
            this.getTable();
        },
        methods: {
            getTable(){
                 // 保留this
                let _this=this;
                this.axios.post('http://localhost:8080/mt/brand/getBrandPage',{
                    "brandDto":{
                        "Category_ids":this.selectedOptions,
                        "name":this.names
                    },
                    "myPagaRequest":{
                        "page":this.page,
                        "limit":this.limit,
                        "sort":this.sort,
                        "dir":this.dir
                    }
                })
                .then(response => {
                    if(response.data.statuCode){
                        this.tableData=response.data.data.content;
                        for(var i=0;i<this.tableData.length;i++){
                            if(this.tableData[i].showOut == 'UnShow'){
                                this.tableData[i].button = '显示';
                            }
                            else{
                                this.tableData[i].button = '冻结'
                            }
                        }
                        this.total=response.data.data.totalElements;
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
            handleCurrentChange(val){
                this.page = val;
                this.getTable();
            },
            search(){
                this.getTable();
            },
            getCategory(){
              this. getOne();
            },
            delAll(){
                for(var i = 0;i < this.multipleSelection.length;i++){
                    this.ids[i] = this.multipleSelection[i].brand_id;
                }
                 // 保留this
                let _this=this;
                this.axios.post('http://localhost:8080/mt/brand/deteleBrand',{
                    "ids":this.ids
                })
                .then(response => {
                    this.getTable();
                    if(response.data.statuCode){
                        this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
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
                this.multipleSelection = [];
            },
            handleSelectionChange(val){
                this.multipleSelection = val;
            },
            handleEdit(row){
                console.log(row);
                var arr=row.Category_id.split("/");
                this.form.name = row.name;
                this.form.category_id=arr;
                this.form.id = row.brand_id;
                this.editVisible = true;
            },
            saveEdit(){
                 // 保留this
                let _this=this;
                this.axios.post('http://localhost:8080/mt/brand/editBrand',{
                    "brand_id":this.form.id,
                    "Category_ids":this.form.category_id,
                    "name":this.form.name
                })
                .then(response => {
                    this.getTable();
                    if(response.data.statuCode){
                        this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
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
                this.editVisible = false;
            },
            handleShow(row){
                // 保留this
                let _this=this;
                this.axios.post('http://localhost:8080/mt/brand/showOut',{
                    "brand_id":row.brand_id
                })
                .then(response => {
                    this.getTable();
                    if(response.data.statuCode){
                        this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
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
            // 获取一级分类
            getOne(){
            // 保留this
            let _this=this;
            this.axios.post('http://localhost:8080/mt/categoryFirst/findAllCategoryFirst',{
                "categoryFirstDto":{},
                "myPagaRequest":{
                    "page":1,
                    "limit":200,
                    "sort":"creatTime",
                    "dir":"DESC"
                }
            })
            .then(response => {
                if(response.data.statuCode){
                    this.options=response.data.data.content;
                    for(var i=0;i<this.options.length;i++){
                        this.options[i].value= this.options[i].categoryFirst_id,
                        this.options[i].label= this.options[i].name
                        if(this.options[i].name == '维修'){
                            this.options[i].children=[];
                            this.getTwo(this.options[i].value);
                        }
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
        },

         getTwo(twoIds){
            // 保留this
            let _this=this;
            this.axios.post('http://localhost:8080/mt/categorySecond/findAllCategorySecond',{
                "categorySecondDto":{
                    'categoryFirst_id':twoIds
                },
                "myPagaRequest":{
                    "page":1,
                    "limit":200,
                    "sort":"creatTime",
                    "dir":"DESC"
                }
            })
            .then(response => {
                if(response.data.statuCode){
                   
                    for(var j=0;j<this.options.length;j++){
                        if(this.options[j].value == twoIds){
                            this.options[j].children = response.data.data.content;
                            for(var i=0;i<this.options[j].children.length;i++){
                                this.options[j].children[i].value= this.options[j].children[i].categorySecond_id,
                                this.options[j].children[i].label= this.options[j].children[i].name
                            }
                        }
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
        },

        add(){
            this.addVisible = true;
        },

        saveAdd(){
            this.addVisible = false;
            if(this.addform.category_id==''){
                 this.$notify.error({
                    title: '请选择所属分类',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
            else if(this.addform.name=='' || this.addform.name==null){
                 this.$notify.error({
                    title: '请输入品牌名',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
            else{
                 // 提交新增品牌
                this.axios.post('http://localhost:8080/mt/brand/addBrand',{
                    "Category_ids":this.addform.category_id,
                    "name":this.addform.name
                })
                .then(response => {
                    if(response.data.statuCode){
                        this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
                        });
                        this.getOne();
                        this.getTable();
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
        }
    }
    }
</script>
<style>
   .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 160px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .red{
        color: #ff0000;
    }
    .mr10{
        margin-right: 10px;
    }
    .pagination{
        text-align: center;  
    }
</style>