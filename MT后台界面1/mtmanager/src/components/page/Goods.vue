<template>
     <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" icon="delete" class="handle-del mr10" @click="delAll">批量删除</el-button>
                <el-cascader expand-trigger="hover" :clearable = 'true' :options="options" class="handle-select mr10" v-model="selectedOptions"  placeholder="所属分类">
                </el-cascader>
                <el-input v-model="names" :clearable = 'true' placeholder="商品名" class="handle-input mr10" ></el-input>
                <el-button type="primary" icon="search" @click="searchGoods">搜索</el-button>
                <el-button type="success" icon="search" @click="add">新增</el-button>
            </div>

             <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <template v-for="norm in props.row.norms">
                                <el-form-item :label="norm.name"><span>{{ norm.value }}</span></el-form-item>
                            </template>
                            <div>
                            <el-form-item label="商品展示图">
                                <img :src="props.row.masterImage" style="width: 50px;height:50px">
                            </el-form-item>
                            <el-form-item label="商品详情图">
                                <template v-for="img in props.row.detailsImage">
                                    <img :src="img.img" style="width: 50px;height:50px;margin-right: 5px;">
                                </template>
                            </el-form-item>
                            </div>
                        </el-form>
                    </template>
                </el-table-column>
                
                <el-table-column label="创建时间" prop="creatTime" width="200" sortable></el-table-column>
                <el-table-column label="商品名称" prop="goodsName" width="180"></el-table-column>
                <el-table-column label="商品分类" prop="category" ></el-table-column>
                <el-table-column label="品牌" prop="brand" width="180" sortable></el-table-column>
                <el-table-column label="操作" width="325" align="center">
                    <template slot-scope="scope">
                        <el-button type="success" @click="">编辑</el-button>
                        <el-button type="success" @click="upOrdown(scope.row)">{{scope.row.show}}</el-button>
                        <el-button type="success" @click="showSKUs">库存计量单位</el-button>
                    </template>
                </el-table-column>
            </el-table>











            <div class="pagination">
                <el-pagination background @current-change="" layout="prev, pager, next" :total="total" :page-size="limit" center>
                </el-pagination>
            </div>
        </div>

         <!-- 新增弹出框 -->
        <el-dialog title="新增商品" :visible.sync="addVisible" width="70%" @closed="cancalAdd">
            <div>
            <el-steps :active="active" finish-status="success">
                <el-step title="商品名称"></el-step>
                <el-step title="所属分类"></el-step>
                <el-step title="所属品牌"></el-step>
                <el-step title="商品展示图"></el-step>
                <el-step title="商品详情图"></el-step>
                <el-step title="设置基础属性"></el-step>
                <!-- <el-step title="库存计量属性"></el-step> -->
            </el-steps>
            </div>
            <el-form ref="addform" :model="addform" label-width="70px">
                <div style="margin-top: 30px;" id="s0">
                    <el-form-item label="商品名称">
                        <el-input v-model="goodsname" autocomplete="off" placeholder="商品名称" class="handle-select1 mr10"></el-input>
                    </el-form-item>
                </div>

                <div style="margin-top: 30px;" id="s1"  hidden>
                    <el-form-item label="所属分类">
                        <el-cascader expand-trigger="hover" :clearable = 'true' :options="options" class="handle-select1 mr10" placeholder="所属分类" @change="getbrand" v-model="selectedaddOptions"></el-cascader>
                    </el-form-item>
                </div>


                <div style="margin-top: 30px;" id="s2" hidden>
                    <el-form-item label="所属品牌">
                        <el-cascader expand-trigger="hover" :clearable = 'true' :options="brandoptions" class="handle-select1 mr10" placeholder="所属品牌" v-model="selectedaddBrandOptions"></el-cascader>
                    </el-form-item>
                </div>


                 <div style="margin-top: 30px;" id="s3" hidden>
                     <template v-for="showcropImg in showcropImgs">
                        <img :src="showcropImg" class="pre-img">
                     </template>
                    <div>
                        <div class="crop-demo-btn">选择图片
                            <input class="crop-input" type="file" name="image" accept="image/*" @change="showSetImage"/>
                        </div>
                        <div class="crop-demo-btn">&nbsp&nbsp&nbsp&nbsp重置
                            <input class="crop-input" type="button" @click="showreset"/>
                        </div>
                    </div>
                </div>


                 <div style="margin-top: 30px;" id="s4" hidden>
                     <template v-for="othercropImg in othercropImgs">
                        <img :src="othercropImg" class="pre-img">
                     </template>
                    <div>
                        <div class="crop-demo-btn">选择图片
                            <input class="crop-input" type="file" name="image" accept="image/*" @change="otherSetImage"/>
                        </div>
                        <div class="crop-demo-btn">&nbsp&nbsp&nbsp&nbsp重置
                            <input class="crop-input" type="button" @click="otherreset"/>
                        </div>
                    </div>
                </div>


                <div style="margin-top: 30px;" id="s5" hidden>
                    <el-table :data="norms" class="tb-edit" style="width:100%" highlight-current-row>
                        <el-table-column prop="name" label="属性名"></el-table-column>
                        <el-table-column  label="属性值">
                            <template slot-scope="scope">
                                <el-input size="small" v-model="scope.row.value" placeholder="请输入内容"></el-input> <span>{{scope.row.value}}</span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>    



                <div style="color: green;font-size: 30px;margin-left: 39%;margin-top: 25px;" id="ok" hidden>
                    <span>信息填写完毕</span>
                </div>

            </el-form>
            <div id='next'>
            <el-button style="margin-top: 12px;" @click="next" >下一步</el-button>
            </div>
            <span slot="footer" class="dialog-footer" hidden id='goodsAdd'>
                <el-button @click="cancalAdd">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="裁剪图片" :show-close="false" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="showdialogVisible" width="30%">
            <vue-cropper ref='cropper1' :src="showImgSrc" :ready="showcropImage" :zoom="showcropImage" :cropmove="showcropImage" style="width:100%;height:100%;" :aspectRatio="1"></vue-cropper>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showcancelP">取 消</el-button>
                <el-button type="primary" @click="showsubmitP">确 定</el-button>
            </span>
        </el-dialog>


        <el-dialog title="裁剪图片" :show-close="false" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="otherdialogVisible" width="30%">
            <vue-cropper ref='cropper2' :src="otherImgSrc" :ready="othercropImage" :zoom="othercropImage" :cropmove="othercropImage" style="width:100%;height:100%;" :aspectRatio="1"></vue-cropper>
            <span slot="footer" class="dialog-footer">
                <el-button @click="othercancelP">取 消</el-button>
                <el-button type="primary" @click="othersubmitP">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 查看库存计量单位 -->
        <el-dialog title="单位库存" :visible.sync="skusdialogTableVisible">
            <el-table :data="tableData">

                <template>
                    <!-- <el-table-column :property="dataItem" :label="dataName" width="150"></el-table-column> -->
                </template>

                <el-table-column label="操作" width="325" align="center">
                    <template slot-scope="scope">
                        <el-button type="success" @click="">编辑</el-button>
                        <el-button type="success" @click="">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>

    </div>
</template>
<script>
    import VueCropper  from 'vue-cropperjs';
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
                skusdialogTableVisible: false,
                multipleSelection: [],
                del_list: [],
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
                brandoptions:[],
                selectedOptions:[],
                selectedaddOptions:[],
                selectedaddBrandOptions:[],
                goodsname:'',
                ids:[],
                active: 0,
                showcropImgs:[],
                showImgSrc:'',
                showdialogVisible:false,
                othercropImgs:[],
                otherImgSrc:'',
                otherdialogVisible:false,
                norms: [],
                deleteId:[]
            }
        },
        mounted(){
            this.getCategory();
            this.getgoods();
        },
        components: {
         VueCropper
        },
        methods: {
            searchGoods(){
                this.getgoods();
            },
            getgoods(){
                // 保留this
                let _this=this;
                this.axios.post('mt/goods/findAllGoods',{
                    "addgoodsDto":{
                        "goodsname":this.names,
                        "selectedaddOptions":this.selectedOptions
                    },
                    "myPagaRequest":{
                        "page":1,
                        "limit":8,
                        "sort":"creatTime",
                        "dir":"DESC"
                    }
                })
                .then(response => {
                    if(response.data.statuCode){
                        console.log(response.data.data.content);
                        this.tableData=response.data.data.content;
                        for(var i=0;i<this.tableData.length;i++){
                            
                             console.log('../../../static/ImageResource/'+this.tableData[i].masterImage+'.png');
                            this.tableData[i].masterImage= require('../../../static/ImageResource/'+this.tableData[i].masterImage+'.png');
                            
                            
                            for(var j = 0;j < this.tableData[i].detailsImage.length;j++){
                                    this.tableData[i].detailsImage[j].img = require('../../../static/ImageResource/'+this.tableData[i].detailsImage[j].img+'.png');
                            }



                            if(this.tableData[i].showOut=="Show"){
                                this.tableData[i].show = "下架"
                            }
                            else{
                                this.tableData[i].show = "上架"
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
            handleSelectionChange(val){
                for(var i = 0 ; i < val.length ; i++){
                    this.deleteId[i] = val[i].goods_id;
                }
            },
            delAll(){
                if(this.deleteId.length == 0){
                     this.$notify.error({
                            title: "请选择要删除的商品",
                            offset: 100,
                            showClose: false
                        });
                    return false;
                }
                // 保留this
                let _this=this;
                this.axios.post('mt/goods/deteleGoods',{
                    "ids":this.deleteId
                })
                .then(response => {
                    this.getgoods();
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
                this.deleteId=[];
            },
            upOrdown(val){
                let _this=this;
                this.axios.post('mt/goods/ShowGoods',{
                    "goods_id":val.goods_id,
                    "name":val.goodsName,
                    "showOut":val.showOut
                })
                .then(response => {
                    this.getgoods();
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
            next() {
                this.active++;
                if(this.active == 0){
                    document.getElementById('s0').removeAttribute('hidden');
                    document.getElementById('s5').setAttribute('hidden','true');
                }
                if(this.active == 1){
                    document.getElementById('s1').removeAttribute('hidden');
                    document.getElementById('s0').setAttribute('hidden','true');
                    if(this.goodsname==''){
                         this.$notify.error({
                            title: "请填写商品名",
                            offset: 100,
                            showClose: false
                         });
                         this.active=this.active-1;
                        document.getElementById('s0').removeAttribute('hidden');
                        document.getElementById('s1').setAttribute('hidden','true');
                    }
                }
                if(this.active == 2){
                    document.getElementById('s2').removeAttribute('hidden');
                    document.getElementById('s1').setAttribute('hidden','true');
                    if(this.selectedaddOptions.length == 0){
                        this.$notify.error({
                            title: '请选择所属分类',
                            offset: 100,
                            showClose: false
                        });
                        this.active--;
                        document.getElementById('s1').removeAttribute('hidden');
                        document.getElementById('s2').setAttribute('hidden','true');
                    }
                }
                if(this.active == 3){
                    document.getElementById('s3').removeAttribute('hidden');
                    document.getElementById('s2').setAttribute('hidden','true');
                    if(this.selectedaddBrandOptions.length == 0){
                        this.$notify.error({
                            title: '请选择所属品牌',
                            offset: 100,
                            showClose: false
                        });
                        this.active--;
                        document.getElementById('s2').removeAttribute('hidden');
                        document.getElementById('s3').setAttribute('hidden','true');
                    }
                }
                if(this.active == 4){
                    document.getElementById('s4').removeAttribute('hidden');
                    document.getElementById('s3').setAttribute('hidden','true');
                    if(this.showcropImgs.length == 0){
                        this.$notify.error({
                            title: '请选择商品展示图',
                            offset: 100,
                            showClose: false
                        });
                        this.active--;
                        document.getElementById('s3').removeAttribute('hidden');
                        document.getElementById('s4').setAttribute('hidden','true');
                    }
                }
                if(this.active == 5){
                    document.getElementById('s5').removeAttribute('hidden');
                    document.getElementById('s4').setAttribute('hidden','true');
                     if(this.othercropImgs.length == 0){
                        this.$notify.error({
                            title: '请选择商品详情图',
                            offset: 100,
                            showClose: false
                        });
                        this.active--;
                        document.getElementById('s4').removeAttribute('hidden');
                        document.getElementById('s5').setAttribute('hidden','true');
                    }
                }
                if(this.active == 6){
                    var k = true;
                    for(var i=0;i<this.norms.length;i++){
                        if(this.norms[i].value=='' || this.norms[i].value==null){
                            k = false;
                        }
                   }
                   if(k){
                        document.getElementById('s5').setAttribute('hidden','true');
                        document.getElementById('goodsAdd').removeAttribute('hidden');
                        document.getElementById('ok').removeAttribute('hidden');
                        document.getElementById('next').setAttribute('hidden','true');
                   }
                   else{
                        this.active--;
                        this.$notify.error({
                                title: '请填写完整基础属性值',
                                offset: 100,
                                showClose: false
                            });
                   }
                }
            },
            showSKUs(){
                this.skusdialogTableVisible = true;
            },
            cancalAdd(){
                this.active=0;
                this.goodsname='';
                this.selectedaddOptions=[];
                this.selectedaddBrandOptions=[];
                this.showcropImgs=[];
                this.othercropImgs=[];
                this.norms=[];
                this.otherImgSrc = '';
                this.showImgSrc = '';
                document.getElementById('s0').removeAttribute('hidden');
                document.getElementById('next').removeAttribute('hidden');
                document.getElementById('s1').setAttribute('hidden','true');
                document.getElementById('s2').setAttribute('hidden','true');
                document.getElementById('s3').setAttribute('hidden','true');
                document.getElementById('s4').setAttribute('hidden','true');
                document.getElementById('s5').setAttribute('hidden','true');
                document.getElementById('ok').setAttribute('hidden','true');
                document.getElementById('goodsAdd').setAttribute('hidden','true');
                this.addVisible = false
            },
            getbrand(){
                if(this.selectedaddOptions.length == 0){
                    this.brandoptions=[];
                    return false;
                }
                //获取基础属性列表
                 this.axios.post('mt/BaseNorms/getBaseNormsPage',{
                "baseNormsDto":{
                    "categoryThird_id":this.selectedaddOptions[2]
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
                   this.norms=response.data.data.content;
                   for(var i=0;i<this.norms.length;i++){
                        this.norms.value=''
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

                //获取品牌列表
                  // 保留this
                let _this=this;
                this.axios.post('mt/brand/getBrandPage',{
                    "brandDto":{
                        "Category_ids":[]
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
                        this.brandoptions=[];
                        var show = response.data.data.content;
                        var b = {};
                        for(var i=0;i<show.length;i++){
                            var a =  show[i].Category_id.split("/");
                            if(a.length == 2){
                                if(a[0] == this.selectedaddOptions[0] && a[1] == this.selectedaddOptions[1]){
                                    b.value= show[i].brand_id;
                                    b.label= show[i].name;
                                    this.brandoptions.push(b);
                                }
                            }
                            else{
                                if(a[0] == this.selectedaddOptions[0]){
                                    b.value= show[i].brand_id;
                                    b.label= show[i].name;
                                    this.brandoptions.push(b);
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




            showreset(){
                this.showcropImgs = [];
            },
            showSetImage(ex){
                const file = ex.target.files[0];
                if (!file.type.includes('image/')) {//判断是否为图片
                    return;
                }
                const readerw = new FileReader();
                readerw.onload = (event) => {
                    this.showdialogVisible = true;
                    this.showImgSrc = event.target.result;
                    this.$refs.cropper1 && this.$refs.cropper1.replace(event.target.result);
                };
                readerw.readAsDataURL(file);
            },
            // 展示截取后图片样式
            showcropImage () {
               
            },
            // 关闭截图后界面，截图完成
            showsubmitP(){
                this.showcropImgs[0]=this.$refs.cropper1.getCroppedCanvas().toDataURL();
                this.showdialogVisible = false;
            },
            // 取消截图界面
            showcancelP(){
                // this.onecropImg = '';//页面创建时赋值默认路径
                this.showdialogVisible = false;
            },



             otherreset(){
                this.othercropImgs = [];
            },
            otherSetImage(e){
                const file = e.target.files[0];
                if (!file.type.includes('image/')) {//判断是否为图片
                    return;
                }
                const reader = new FileReader();
                reader.onload = (event) => {
                    this.otherdialogVisible = true;
                    this.otherImgSrc = event.target.result;
                    this.$refs.cropper2 && this.$refs.cropper2.replace(event.target.result);
                };
                reader.readAsDataURL(file);
            },
            // 展示截取后图片样式
            othercropImage () {
               
            },
            // 关闭截图后界面，截图完成
            othersubmitP(){
                this.othercropImgs.push(this.$refs.cropper2.getCroppedCanvas().toDataURL());
                this.otherdialogVisible = false;
            },
            // 取消截图界面
            othercancelP(){
                // this.onecropImg = '';//页面创建时赋值默认路径
                this.otherdialogVisible = false;
            },



            getCategory(){
              this. getOne();
            },
            // 获取一级分类
            getOne(){
            // 保留this
            let _this=this;
            this.axios.post('mt/categoryFirst/findAllCategoryFirst',{
                "categoryFirstDto":{
                   "type":1
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
                    this.options=response.data.data.content;
                    for(var i=0;i<this.options.length;i++){
                        this.options[i].value= this.options[i].categoryFirst_id,
                        this.options[i].label= this.options[i].name
                        this.options[i].children=[];
                        this.getTwo(this.options[i].value);
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
            this.axios.post('mt/categorySecond/findAllCategorySecond',{
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
                                this.options[j].children[i].children=[];
                                this.getThree(this.options[j].children[i].value);
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
        getThree(threeIds){
            // 保留this
            let _this=this;
            this.axios.post('mt/categoryThird/findAllCategoryThird',{
                "categoryThirdDto":{
                    'categorySecond_id':threeIds
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
                    for(var h=0;h<this.options.length;h++){
                        for(var j=0;j<this.options[h].children.length;j++){
                            if(this.options[h].children[j].value == threeIds){
                                this.options[h].children[j].children = response.data.data.content;
                                for(var i=0;i<this.options[h].children[j].children.length;i++){
                                    this.options[h].children[j].children[i].value = this.options[h].children[j].children[i].categoryThird_id;
                                    this.options[h].children[j].children[i].label = this.options[h].children[j].children[i].name;
                                }
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
// -----------------------------------------------------------------------------------------------------------------------------------添加商品
        saveAdd(){
            var normsDto=[];
            for(var i=0;i<this.norms.length;i++){
                var s={
                name:'',
                value:''
                }
                s.name = this.norms[i].name;
                s.value = this.norms[i].value;
                normsDto.push(s);
            }

            let _this = this;
            this.axios.post('mt/goods/addGoods',{
                    "goodsname":_this.goodsname,
                    "selectedaddOptions":_this.selectedaddOptions,
                    "selectedaddBrandOptions":_this.selectedaddBrandOptions,
                    "showcropImgs":_this.showcropImgs,
                    "othercropImgs":_this.othercropImgs,
                    "normsDtos":normsDto
            })
            .then(response => {
                _this.getgoods();
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

            this.active=0;
            this.goodsname='';//商品名
            this.selectedaddOptions=[];//分类
            this.selectedaddBrandOptions=[];//品牌
            this.showcropImgs=[];//商品展示图片
            this.othercropImgs=[];//商品详情图片
            this.norms=[];//基础属性
            this.otherImgSrc = '';
            this.showImgSrc = '';
            document.getElementById('s0').removeAttribute('hidden');
            document.getElementById('next').removeAttribute('hidden');
            document.getElementById('s1').setAttribute('hidden','true');
            document.getElementById('s2').setAttribute('hidden','true');
            document.getElementById('s3').setAttribute('hidden','true');
            document.getElementById('s4').setAttribute('hidden','true');
            document.getElementById('s5').setAttribute('hidden','true');
            document.getElementById('ok').setAttribute('hidden','true');
            document.getElementById('goodsAdd').setAttribute('hidden','true');
            this.addVisible = false
        }
    }
    }
</script>
<style>
   .handle-box {
        margin-bottom: 20px;
    }
    .el-table-add-row {
        margin-top: 10px;
        width: 100%;
        height: 34px;
        border: 1px dashed #c1c1cd;
        border-radius: 3px;
        cursor: pointer;
        justify-content: center;
        display: flex;
        line-height: 34px;
    }

    .demo-table-expand {
    font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }

    .pre-img{   
        width: 120px;
        height: 120px;
        border:1px dashed #d9d9d9;
        border-radius: 6px;
        margin-right: 10px;
    }

    .tb-edit .el-input {
    display: none
    }
    .tb-edit .current-row .el-input {
        display: block
    }
    .tb-edit .current-row .el-input+span {
        display: none
    }

    .crop-demo-btn{
        position: relative;
        width: 100px;
        height: 40px;
        line-height: 40px;
        padding: 0 20px;
        float:right;
        margin-right: 5px;
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

    .handle-select {
        width: 250px;
    }

    .handle-select1 {
        width: 250px;
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