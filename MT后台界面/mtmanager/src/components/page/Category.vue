<template>
    <div>
         <el-row :gutter="20">
             <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>一级分类</span>
                        <el-button @click="oneAdd($event)" style="float: right; padding: 3px 0" type="text">新增</el-button>
                    </div>
                     <el-table :data="one" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;height:400px;">
                        <el-table-column width="80" padding="0px">
                            <template slot-scope="scope">
                                <div class="todo-item">{{scope.row.name}}</div>
                            </template>
                        </el-table-column>
                         <el-table-column prop="comImage" align="center" :show-overflow-tooltip = "true" >
                             <template slot-scope="scope">
                                  <img :src="scope.row.img" style="width: 50px;height:50px">
                             </template>
                        </el-table-column>
                        <el-table-column  padding="0px">
                            <template slot-scope="scope" width="120">
                                <!-- <el-button @click="oneEdithandleClick(scope.row)" type="text" size="small" icon="el-icon-edit" style="padding:0" :value="scope.row.categoryFirst_id">编辑</el-button> -->
                                <el-button @click="oneDeletehandleClick(scope.row)" type="text" size="small" icon="el-icon-lx-delete" style="padding: 0" :value="scope.row.categoryFirst_id">删除</el-button>
                                <el-button @click="oneNexthandleClick(scope.row)" type="text" size="small" icon="el-icon-lx-right" style="padding: 0;" :value="scope.row.categoryFirst_id">下级</el-button>
                                <el-button @click="oneShowClick(scope.row)" type="text" size="small"  style="padding: 0;margin-left: 40px" :value="scope.row.categoryFirst_id">{{scope.row.button}}</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="OneTotalNum" :page-size="pageSize"  @current-change="onehandleCurrentChange"></el-pagination>
                    </div>
                </el-card>
            </el-col>



        <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>{{twoName}}二级分类</span>
                        <el-button @click="twoAdd($event)" style="float: right; padding: 3px 0" type="text">新增</el-button>
                    </div>
                     <el-table :data="two" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;height:400px;">
                        <el-table-column width="80">
                            <template slot-scope="scope">
                                <div class="todo-item">{{scope.row.name}}</div>
                            </template>
                        </el-table-column>
                         <el-table-column prop="comImage" align="center" :show-overflow-tooltip = "true" >
                             <template slot-scope="scope">
                                  <img :src="scope.row.img" style="width: 50px;height:50px">
                             </template>
                        </el-table-column>
                        <el-table-column  padding="0px">
                            <template slot-scope="scope" width="120">
                                <!-- <el-button @click="twoEdithandleClick(scope.row)" type="text" size="small" icon="el-icon-edit" style="padding:0" :value="scope.row.categorySecond_id">编辑</el-button> -->
                                <el-button @click="twoDeletehandleClick(scope.row)" type="text" size="small" icon="el-icon-lx-delete" style="padding: 0" :value="scope.row.categorySecond_id">删除</el-button>
                                <el-button @click="twoNexthandleClick(scope.row)" type="text" size="small" icon="el-icon-lx-right" style="padding: 0;" :value="scope.row.categorySecond_id">下级</el-button>
                                <el-button @click="twoShowClick(scope.row)" type="text" size="small"  style="padding: 0;margin-left: 40px" :value="scope.row.categorySecond_id">{{scope.row.button}}</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="TwoTotalNum" :page-size="pageSize"  @current-change="twohandleCurrentChange"></el-pagination>
                    </div>
                </el-card>
            </el-col>





        
            <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>{{threeName}}三级分类</span>
                        <el-button @click="threeAdd($event)" style="float: right; padding: 3px 0" type="text">新增</el-button>
                    </div>
                     <el-table :data="three" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;height:400px;">
                        <el-table-column width="80">
                            <template slot-scope="scope">
                                <div class="todo-item">{{scope.row.name}}</div>
                            </template>
                        </el-table-column>
                         <el-table-column prop="comImage" align="center" :show-overflow-tooltip = "true" >
                             <template slot-scope="scope">
                                  <img :src="scope.row.img" style="width: 50px;height:50px">
                             </template>
                        </el-table-column>
                        <el-table-column  padding="0px" width="120">
                            <template slot-scope="scope">
                                <!-- <el-button @click="threeEdithandleClick(scope.row)" type="text" size="small" icon="el-icon-edit" style="padding:0" :value="scope.row.categoryThird_id">编辑</el-button> -->
                                <el-button @click="threeDeletehandleClick(scope.row)" type="text" size="small" icon="el-icon-lx-delete" style="padding: 0" :value="scope.row.categoryThird_id">删除</el-button>
                                <el-button @click="threeShowClick(scope.row)" type="text" size="small"  style="padding: 0;" :value="scope.row.categoryThird_id">{{scope.row.button}}</el-button>
                                <el-button @click="threeMakeClick(scope.row)" type="text" size="small"  style="padding: 0;" :value="scope.row.categoryThird_id">基础属性</el-button>
                                <el-button @click="threebaseClick(scope.row)" type="text" size="small"  style="padding: 0;" :value="scope.row.categoryThird_id">库存计量属性</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="ThreeTotalNum" :page-size="pageSize"  @current-change="threehandleCurrentChange"></el-pagination>
                    </div>
                </el-card>
            </el-col>
         </el-row>

         <el-dialog title="新增一级分类" :visible.sync="addOne" width="30%" :show-close="false" center>
            <el-form :model="addOneForm"  ref="addOneForm">
                <div class="crop-demo">
                    <img :src="onecropImg" class="pre-img" id="oneCP" hidden>
                    <div class="crop-demo-btn">选择图片
                        <input class="crop-input" type="file" name="image" accept="image/*" @change="oneSetImage"/>
                    </div>
                </div>
                <el-form-item prop="onename">
                    <el-input v-model="addOneForm.onename" autocomplete="off" placeholder="一级分类"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addOneCancel">取 消</el-button>
                <el-button type="primary" @click="addOneSubmit('addOneForm')">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="裁剪图片" :show-close="false" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="onedialogVisible" width="30%">
            <vue-cropper ref='cropper' :src="oneImgSrc" :ready="onecropImage" :zoom="onecropImage" :cropmove="onecropImage" style="width:100%;height:100%;":aspectRatio="1"></vue-cropper>
            <span slot="footer" class="dialog-footer">
                <el-button @click="onecancelP">取 消</el-button>
                <el-button type="primary" @click="onesubmitP">确 定</el-button>
            </span>
        </el-dialog>


        <el-dialog title="新增二级分类" :visible.sync="addTwo" width="30%" :show-close="false" center>
            <el-form :model="addTwoForm"  ref="addTwoForm">
                <div class="crop-demo">
                    <img :src="twocropImg" class="pre-img" id="twoCP" hidden>
                    <div class="crop-demo-btn">选择图片
                        <input class="crop-input" type="file" name="image" accept="image/*" @change="twoSetImage"/>
                    </div>
                </div>
                <el-form-item prop="ontwoname">
                    <el-input v-model="addTwoForm.twoname" autocomplete="off" placeholder="二级分类"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addTwoCancel">取 消</el-button>
                <el-button type="primary" @click="addTwoSubmit('addTwoForm')">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="裁剪图片" :show-close="false" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="twodialogVisible" width="30%">
            <vue-cropper ref='cropper' :src="twoImgSrc" :ready="twocropImage" :zoom="twocropImage" :cropmove="twocropImage" style="width:100%;height:100%;":aspectRatio="1"></vue-cropper>
            <span slot="footer" class="dialog-footer">
                <el-button @click="twocancelP">取 消</el-button>
                <el-button type="primary" @click="twosubmitP">确 定</el-button>
            </span>
        </el-dialog>


        <el-dialog title="新增三级分类" :visible.sync="addThree" width="30%" :show-close="false" center>
            <el-form :model="addThreeForm"  ref="addThreeForm">
                <div class="crop-demo">
                    <img :src="threecropImg" class="pre-img" id="threeCP" hidden>
                    <div class="crop-demo-btn">选择图片
                        <input class="crop-input" type="file" name="image" accept="image/*" @change="threeSetImage"/>
                    </div>
                </div>
                <el-form-item prop="onthreename">
                    <el-input v-model="addThreeForm.threename" autocomplete="off" placeholder="三级分类"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addThreeCancel">取 消</el-button>
                <el-button type="primary" @click="addThreeSubmit('addThreeForm')">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="裁剪图片" :show-close="false" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="threedialogVisible" width="30%">
            <vue-cropper ref='cropper' :src="threeImgSrc" :ready="threecropImage" :zoom="threecropImage" :cropmove="threecropImage" style="width:100%;height:100%;":aspectRatio="1"></vue-cropper>
            <span slot="footer" class="dialog-footer">
                <el-button @click="threecancelP">取 消</el-button>
                <el-button type="primary" @click="threesubmitP">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="基础属性" :visible.sync="baseNorms" width="40%"  center>
             <div>
                <el-button @click="AddbaseNorms($event)" type="text">新增</el-button>
            </div>
            <template v-for="item in items">
                <el-button type="primary" size="mini" style="margin-bottom: 10px;margin-left:10px"  :value="item.baseNorms_id" @click="DeletebaseNorms($event)">{{item.name}}</el-button>
            </template>
        </el-dialog>

        <el-dialog title="新增基础属性" :visible.sync="addbaseNorms" width="25%"  center>
              <el-form :model="addbaseNormsForm"  ref="addThreeForm">
                <el-form-item prop="onthreename">
                    <el-input v-model="addbaseNormsForm.name" autocomplete="off" placeholder="基础属性名"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addbaseNorms = false">取 消</el-button>
                <el-button type="primary" @click="addbaseNormsSubmit('addbaseNormsForm')">确 定</el-button>
            </div>
        </el-dialog>


        <el-dialog title="库存计量属性" :visible.sync="baseSKU" width="40%"  center>
             <div>
                <el-button @click="AddbaseSKU($event)" type="text">新增</el-button>
            </div>
            <template v-for="item in itemskus">
                <el-button type="primary" size="mini" style="margin-bottom: 10px;margin-left:10px"  :value="item.baseSKU_id" @click="DeletebaseSKU($event)">{{item.name}}</el-button>
            </template>
        </el-dialog>

        <el-dialog title="新增库存计量属性" :visible.sync="addbaseSKU" width="25%"  center>
              <el-form :model="addbaseSKUForm"  ref="addThreeForm">
                <el-form-item prop="onthreename">
                    <el-input v-model="addbaseSKUForm.name" autocomplete="off" placeholder="基础属性名"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addbaseSKU = false">取 消</el-button>
                <el-button type="primary" @click="addbaseSKUSubmit('addbaseSKUForm')">确 定</el-button>
            </div>
        </el-dialog>


    </div>
</template>
<script>
    import VueCropper  from 'vue-cropperjs';
    export default {
         data:function() {
            return {
                pageSize : 5,// 一页显示记录数
                sort:"creatTime",// 排序标记
                dir:"DESC",// 倒序
// ------------------------------------------------------一级分类

                twoName:'',

                addOne:false,// 添加一级分类弹窗是否显示
                addOneForm:{},// 一级分类弹窗表达数据
                onecropImg:'',// 一级分类截图
                onedialogVisible:false,// 截图弹窗是否显示
                oneImgSrc:'',// 截取得到的base64图数据
                oneI:false,// 是否有选择图片，代表截图完成

                OneTotalNum : 1,// 总记录数
                OnePage:1,//记录当前页数

                oneEdit:'',

// ------------------------------------------------------二级分类

                threeName:'',

                addTwo:false,
                addTwoForm:{},
                twocropImg:'',
                twodialogVisible:false,
                twoImgSrc:'',
                twoI:false,

                TwoTotalNum : 1,// 总记录数
                TwoPage:1,//记录当前页数

                twoEdit:'',

// ------------------------------------------------------三级分类

                threeId:'',//记录父级分类id

                addThree:false,
                addThreeForm:{},
                threecropImg:'',
                threedialogVisible:false,
                threeImgSrc:'',
                threeI:false,

                ThreeTotalNum : 1,// 总记录数
                ThreePage:1,//记录当前页数

                threeEdit:'',

//-------------------------------------------------------列表数据

                one: [],
                two: [],
                three: [],
                
                baseNorms:false,
                fatherId:'',
                addbaseNorms:false,
                addbaseNormsForm:{
                    name:''
                },
                items:[],


                baseSKU:false,
                addbaseSKU:false,
                addbaseSKUForm:{
                    name:''
                },
                itemskus:[]
            }
    },
    //页面加载函数
    mounted(){
        this.getOne();
        this.getTwo();
        this.getThree();
    },
    components: {
        VueCropper
    },
    methods: {
        threeMakeClick(row){
            this.baseNorms = true;
            this.fatherId = row.categoryThird_id;
            this.getbaseNorms();
        },
        AddbaseNorms(){
            this.addbaseNorms = true;
        },
        addbaseNormsSubmit(formName){
               // 提交新增品牌
                this.axios.post('http://localhost:8080/mt/BaseNorms/addBaseNorms',{
                    "categoryThird_id":this.fatherId,
                    "name":this.addbaseNormsForm.name
                })
                .then(response => {
                    if(response.data.statuCode){
                        this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
                        });
                        this.getbaseNorms();
                        this.addbaseNorms = false;
                        this.addbaseNormsForm.name = '';
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
        DeletebaseNorms(e){
            let id = e.currentTarget.value;
            this.$confirm('此操作将永久删除该基础属性, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {


                    // 删除新增品牌
                    this.axios.post('http://localhost:8080/mt/BaseNorms/deleteBaseNorms',{
                        "baseNorms_id":id
                    })
                    .then(response => {
                        if(response.data.statuCode){
                            this.$notify({
                                title: response.data.message,
                                type: 'success',
                                offset: 100,
                                showClose: false
                            });
                            this.getbaseNorms();
                        }
                        else{
                                this.$notify.error({
                                title: response.data.message,
                                offset: 100,
                                showClose: false
                            });
                        }
                    });


                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        },
        getbaseNorms(){
            // 提交新增品牌
            this.axios.post('http://localhost:8080/mt/BaseNorms/getBaseNormsPage',{
                "baseNormsDto":{
                    "categoryThird_id":this.fatherId
                },
                "myPagaRequest":{
                    "page":1,
                    "limit":200,
                    "sort":this.sort,
                    "dir":this.dir
                }
            })
            .then(response => {
                if(response.data.statuCode){
                   this.items=response.data.data.content;
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


// --------------------------------------------------------------------------------------------

        threebaseClick(row){
            this.baseSKU = true;
            this.fatherId = row.categoryThird_id;
            this.getbaseSKU();
        },
        AddbaseSKU(){
            this.addbaseSKU = true;
        },
        addbaseSKUSubmit(formName){
               // 提交新增品牌
                this.axios.post('http://localhost:8080/mt/BaseSKU/addBaseSKU',{
                    "categoryThird_id":this.fatherId,
                    "name":this.addbaseSKUForm.name
                })
                .then(response => {
                    if(response.data.statuCode){
                        this.$notify({
                            title: response.data.message,
                            type: 'success',
                            offset: 100,
                            showClose: false
                        });
                        this.getbaseSKU();
                        this.addbaseSKU = false;
                        this.addbaseSKUForm.name = '';
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
        DeletebaseSKU(e){
            let id = e.currentTarget.value;
            this.$confirm('此操作将永久删除该库存计量属性, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {


                    // 删除新增品牌
                    this.axios.post('http://localhost:8080/mt/BaseSKU/deleteBaseSKU',{
                        "baseSKU_id":id
                    })
                    .then(response => {
                        if(response.data.statuCode){
                            this.$notify({
                                title: response.data.message,
                                type: 'success',
                                offset: 100,
                                showClose: false
                            });
                            this.getbaseSKU();
                        }
                        else{
                                this.$notify.error({
                                title: response.data.message,
                                offset: 100,
                                showClose: false
                            });
                        }
                    });


                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        },
        getbaseSKU(){
            // 获取新增品牌
            this.axios.post('http://localhost:8080/mt/BaseSKU/getBaseSKUPage',{
                "baseSKUDto":{
                    "categoryThird_id":this.fatherId
                },
                "myPagaRequest":{
                    "page":1,
                    "limit":200,
                    "sort":this.sort,
                    "dir":this.dir
                }
            })
            .then(response => {
                if(response.data.statuCode){
                   this.itemskus=response.data.data.content;
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





        // 页面加载时获取一级分类列表
        getOne(){
            // 保留this
            let _this=this;
            this.axios.post('http://localhost:8080/mt/categoryFirst/findAllCategoryFirst',{
                "categoryFirstDto":{},
                "myPagaRequest":{
                    "page":this.OnePage,
                    "limit":this.pageSize,
                    "sort":this.sort,
                    "dir":this.dir
                }
            })
            .then(response => {
                if(response.data.statuCode){
                    this.one=response.data.data.content;
                    for(var i=0;i<this.one.length;i++){
                        this.one[i].img=require('../../../static/categoryFirstImg/'+this.one[i].img+'.png');
                        if(this.one[i].showOut == 'UnShow'){
                            this.one[i].button = '显示';
                        }
                        else{
                            this.one[i].button = '冻结'
                        }
                    }
                    this.OneTotalNum=response.data.data.totalElements;
                    this.OneCurrentPages=1;
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

        getTwo(){
            // 保留this
            let _this=this;
            this.axios.post('http://localhost:8080/mt/categorySecond/findAllCategorySecond',{
                "categorySecondDto":{
                    'categoryFirst_id':sessionStorage.getItem('twoId')
                },
                "myPagaRequest":{
                    "page":this.TwoPage,
                    "limit":this.pageSize,
                    "sort":this.sort,
                    "dir":this.dir
                }
            })
            .then(response => {
                if(response.data.statuCode){
                    if(sessionStorage.getItem('twoId')!=null && sessionStorage.getItem('twoId') !=''){
                        _this.twoName = sessionStorage.getItem('twoName') + '  ';
                        this.two=response.data.data.content;
                        for(var i=0;i<this.two.length;i++){
                            this.two[i].img=require('../../../static/categorySecondImg/'+this.two[i].img+'.png');
                            if(this.two[i].showOut == 'UnShow'){
                                this.two[i].button = '显示';
                            }
                            else{
                                this.two[i].button = '冻结';
                            }
                        }
                        this.TwoTotalNum=response.data.data.totalElements;
                        this.TwoCurrentPages=1;
                    }
                    else{
                        this.two = [];
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



         getThree(){
            // 保留this
            let _this=this;
            this.axios.post('http://localhost:8080/mt/categoryThird/findAllCategoryThird',{
                "categoryThirdDto":{
                    'categorySecond_id':sessionStorage.getItem('threeId')
                },
                "myPagaRequest":{
                    "page":this.ThreePage,
                    "limit":4,
                    "sort":this.sort,
                    "dir":this.dir
                }
            })
            .then(response => {
                if(response.data.statuCode){
                   if(sessionStorage.getItem('threeId')!=null && sessionStorage.getItem('threeId') !=''){
                        _this.threeName = sessionStorage.getItem('threeName') + '  ';
                        this.three=response.data.data.content;
                        for(var i=0;i<this.three.length;i++){
                            this.three[i].img=require('../../../static/categoryThirdImg/'+this.three[i].img+'.png');
                             if(this.three[i].showOut == 'UnShow'){
                                this.three[i].button = '显示';
                            }
                            else{
                                this.three[i].button = '冻结'
                            }
                        }
                        this.ThreeTotalNum=response.data.data.totalElements;
                        this.ThreeCurrentPages=1;
                    }
                    else{
                        this.three = [];
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


// ---------------------------------------------------一级分类------------------------------------------------------
       // 分页行点击触发事件，val是点击的页数
        onehandleCurrentChange(val){
            this.OnePage = val;
            this.OneCurrentPages = val;
            this.getOne();
        },
        // 修改分类
        oneEdithandleClick(one){
            this.oneEdit = one.categoryFirst_id;
        },
        // 删除分类
        oneDeletehandleClick(one){
            this.axios.post('http://localhost:8080/mt/categoryFirst/deleteCategoryFirst',{
                "categoryFirst_id":one.categoryFirst_id
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
        // 下一级分类列表
        oneNexthandleClick(one){
            sessionStorage.setItem('twoId',one.categoryFirst_id);
            sessionStorage.setItem('twoName',one.name);
            sessionStorage.setItem('threeId','');
            sessionStorage.setItem('threeName','');
            this.threeName = '';
            this.three = [];
            this.getTwo();
        },

        oneShowClick(one){
           this.axios.post('http://localhost:8080/mt/categoryFirst/lockCategoryFirst',{
                "categoryFirst_id":one.categoryFirst_id
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

        // 增加一级分类
        oneAdd(e){
            this.addOne = true;
        },
        // 更换分类默认图片
        oneSetImage(e){
            const file = e.target.files[0];
            if (!file.type.includes('image/')) {//判断是否为图片
                return;
            }
            const reader = new FileReader();
            reader.onload = (event) => {
                this.onedialogVisible = true;
                this.oneImgSrc = event.target.result;
                this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
            };
            reader.readAsDataURL(file);
        },
        // 展示截取后图片样式
        onecropImage () {
            this.onecropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
        },
        // 关闭截图后界面，截图完成
        onesubmitP(){
            this.oneI = true;
            document.getElementById('oneCP').removeAttribute('hidden');
            this.onedialogVisible = false;
        },
        // 取消截图界面
        onecancelP(){
            this.oneI = false;
            this.onecropImg = '';//页面创建时赋值默认路径
            this.onedialogVisible = false;
        },

        // 取消添加一级分类
        addOneCancel(){
            this.oneI = false;
            this.onecropImg = '';//页面创建时赋值默认路径
            document.getElementById('oneCP').setAttribute('hidden','true');
            this.addOneForm.onename = null;
            this.addOne = false;
        },
        // 添加一级分类
        addOneSubmit(){
            let _this=this;
            var imgNew = null;
            if(_this.oneI){
                imgNew = _this.onecropImg;
            }
            else{
                this.$notify.error({
                    title: '请选择图片',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
            if(this.addOneForm.onename==null ||this.addOneForm.onename == ''||this.addOneForm.onename==' '){
                this.$notify.error({
                    title: '请填写分类名',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
             // 提交新增一级分类
            this.axios.post('http://localhost:8080/mt/categoryFirst/addCategoryFirst',{
                "categoryFirst_id":this.uuid(),
                "img":imgNew,
                "name":this.addOneForm.onename
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
                }
                else{
                        this.$notify.error({
                        title: response.data.message,
                        offset: 100,
                        showClose: false
                    });
                }
            });
            this.oneI = false;
            this.onecropImg = '';//页面创建时赋值默认路径
            document.getElementById('oneCP').setAttribute('hidden','true');
            this.addOneForm.onename = null;
            this.addOne = false;
        },


//--------------------------------------------------------------------------二级分类


         // 分页行点击触发事件，val是点击的页数
        twohandleCurrentChange(val){
            this.TwoPage = val;
            this.TwoCurrentPages = val;
            this.getTwo();
        },
        // 修改分类
        twoEdithandleClick(two){
            alert(two.categorySecond_id);
        },
        // 删除分类
        twoDeletehandleClick(two){
             this.axios.post('http://localhost:8080/mt/categorySecond/deleteCategorySecond',{
                "categorySecond_id":two.categorySecond_id
            })
            .then(response => {
                if(response.data.statuCode){
                    this.$notify({
                        title: response.data.message,
                        type: 'success',
                        offset: 100,
                        showClose: false
                    });
                    this.getTwo();
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
        // 下一级分类列表
        twoNexthandleClick(two){
            sessionStorage.setItem('threeId',two.categorySecond_id);
            sessionStorage.setItem('threeName',two.name);
            this.getThree();
        },

        twoShowClick(two){
            this.axios.post('http://localhost:8080/mt/categorySecond/lockCategorySecond',{
                 "categorySecond_id":two.categorySecond_id
            })
            .then(response => {
                if(response.data.statuCode){
                    this.$notify({
                        title: response.data.message,
                        type: 'success',
                        offset: 100,
                        showClose: false
                    });
                    this.getTwo();
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

        // 增加二级分类
        twoAdd(e){
            this.addTwo = true;
        },
        // 更换分类默认图片
        twoSetImage(e){
            const file = e.target.files[0];
            if (!file.type.includes('image/')) {//判断是否为图片
                return;
            }
            const reader = new FileReader();
            reader.onload = (event) => {
                this.twodialogVisible = true;
                this.twoImgSrc = event.target.result;
                this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
            };
            reader.readAsDataURL(file);
        },
        // 展示截取后图片样式
        twocropImage () {
            this.twocropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
        },
        // 关闭截图后界面，截图完成
        twosubmitP(){
            this.twoI = true;
            document.getElementById('twoCP').removeAttribute('hidden');
            this.twodialogVisible = false;
        },
        // 取消截图界面
        twocancelP(){
            this.twoI = false;
            this.twocropImg = '';//页面创建时赋值默认路径
            this.twodialogVisible = false;
        },

        // 取消添加二级分类
        addTwoCancel(){
            this.twoI = false;
            this.twocropImg = '';//页面创建时赋值默认路径
            document.getElementById('twoCP').setAttribute('hidden','true');
            this.addTwoForm.twoname = null;
            this.addTwo = false;
        },
        // 添加二级分类
        addTwoSubmit(){
            let _this=this;
            var imgNew = null;
            if(_this.twoI){
                imgNew = _this.twocropImg;
            }
            else{
                this.$notify.error({
                    title: '请选择图片',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
            if(this.addTwoForm.twoname==null ||this.addTwoForm.twoname == ''||this.addTwoForm.twoname==' '){
                this.$notify.error({
                    title: '请填写分类名',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
             // 提交新增二级分类
            this.axios.post('http://localhost:8080/mt/categorySecond/addCategorySecond',{
                    "categorySecond_id":this.uuid(),
                    "img":imgNew,
                    "name":this.addTwoForm.twoname,
                    "categoryFirst_id":sessionStorage.getItem('twoId')
            })
            .then(response => {
                if(response.data.statuCode){
                    this.$notify({
                        title: response.data.message,
                        type: 'success',
                        offset: 100,
                        showClose: false
                    });
                    this.getTwo();
                }
                else{
                        this.$notify.error({
                        title: response.data.message,
                        offset: 100,
                        showClose: false
                    });
                }
            });
            this.twoI = false;
            this.twocropImg = '';//页面创建时赋值默认路径
            document.getElementById('twoCP').setAttribute('hidden','true');
            this.addTwoForm.twoname = null;
            this.addTwo = false;
        },

//--------------------------------------------------------------------------三级分类

        // 分页行点击触发事件，val是点击的页数
        threehandleCurrentChange(val){
            this.ThreePage = val;
            this.ThreeCurrentPages = val;
            this.getThree();
        },
        // 修改分类
        threeEdithandleClick(three){
            alert(three.categoryThird_id);
        },
        // 删除分类
        threeDeletehandleClick(three){
             this.axios.post('http://localhost:8080/mt/categoryThird/deleteCategoryThird',{
                "categoryThird_id":three.categoryThird_id
            })
            .then(response => {
                if(response.data.statuCode){
                    this.$notify({
                        title: response.data.message,
                        type: 'success',
                        offset: 100,
                        showClose: false
                    });
                    this.getThree();
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

        threeShowClick(three){
            this.axios.post('http://localhost:8080/mt/categoryThird/lockCategoryThird',{
                "categoryThird_id":three.categoryThird_id
            })
            .then(response => {
                if(response.data.statuCode){
                    this.$notify({
                        title: response.data.message,
                        type: 'success',
                        offset: 100,
                        showClose: false
                    });
                    this.getThree();
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

        // 增加二级分类
        threeAdd(e){
            this.addThree = true;
        },
        // 更换分类默认图片
        threeSetImage(e){
            const file = e.target.files[0];
            if (!file.type.includes('image/')) {//判断是否为图片
                return;
            }
            const reader = new FileReader();
            reader.onload = (event) => {
                this.threedialogVisible = true;
                this.threeImgSrc = event.target.result;
                this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
            };
            reader.readAsDataURL(file);
        },
        // 展示截取后图片样式
        threecropImage () {
            this.threecropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
        },
        // 关闭截图后界面，截图完成
        threesubmitP(){
            this.threeI = true;
            document.getElementById('threeCP').removeAttribute('hidden');
            this.threedialogVisible = false;
        },
        // 取消截图界面
        threecancelP(){
            this.threeI = false;
            this.threecropImg = '';//页面创建时赋值默认路径
            this.threedialogVisible = false;
        },

        // 取消添加二级分类
        addThreeCancel(){
            this.threeI = false;
            this.threecropImg = '';//页面创建时赋值默认路径
            document.getElementById('threeCP').setAttribute('hidden','true');
            this.addThreeForm.threename = null;
            this.addThree = false;
        },
        // 添加二级分类
        addThreeSubmit(){
            let _this=this;
            var imgNew = null;
            if(_this.threeI){
                imgNew = _this.threecropImg;
            }
            else{
                this.$notify.error({
                    title: '请选择图片',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
            if(this.addThreeForm.threename==null ||this.addThreeForm.threename == ''||this.addThreeForm.threename==' '){
                this.$notify.error({
                    title: '请填写分类名',
                    offset: 100,
                    showClose: false
                });
                return false;
            }
             // 提交新增二级分类
            this.axios.post('http://localhost:8080/mt/categoryThird/addCategoryThird',{
                    "categoryThird_id":this.uuid(),
                    "img":imgNew,
                    "name":this.addThreeForm.threename,
                    "categorySecond_id":sessionStorage.getItem('threeId')
            })
            .then(response => {
                if(response.data.statuCode){
                    this.$notify({
                        title: response.data.message,
                        type: 'success',
                        offset: 100,
                        showClose: false
                    });
                    this.getThree();
                }
                else{
                        this.$notify.error({
                        title: response.data.message,
                        offset: 100,
                        showClose: false
                    });
                }
            });
            this.threeI = false;
            this.threecropImg = '';//页面创建时赋值默认路径
            document.getElementById('threeCP').setAttribute('hidden','true');
            this.addThreeForm.threename = null;
            this.addThree = false;
        },



//---------------------------------------------------------------------------工具

        // 生成uuid的工具
        uuid() {
            var s = [];
            var hexDigits = "0123456789abcdef";
            for (var i = 0; i < 36; i++) {
                s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
            }
            s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
            s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
            s[8] = s[13] = s[18] = s[23] = "-";
        
            var uuid = s.join("");
            return uuid;
        }
    }
}
</script>
<style>
    .page{
        text-align: center;  
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
        margin-left: 36%;
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