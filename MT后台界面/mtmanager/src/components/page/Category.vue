<template>
    <div>
         <el-row :gutter="20">
             <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>一级分类</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                     <el-table :data="one" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;height:400px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                         <el-table-column prop="comImage" align="center" :show-overflow-tooltip = "true">
                             <template slot-scope="scope">
                                  <img :src="scope.row.img" style="width: 50px;height:50px">
                             </template>
                        </el-table-column>
                        <el-table-column width="120" padding="0px">
                            <template slot-scope="scope">
                                <el-button @click="handleClick(scope.row)" type="text" size="small" icon="el-icon-edit" style="padding:0">编辑</el-button>
                                <el-button type="text" size="small" icon="el-icon-lx-delete" style="padding: 0">删除</el-button>
                                <el-button type="text" size="small" icon="el-icon-lx-right" style="padding: 0;margin-left: 28px">下级</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="OneTotalNum" :page-size="OnePageSize" :current-page.sync="OneCurrentPages"></el-pagination>
                    </div>
                </el-card>
            </el-col>



            <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>二级分类</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                     <el-table :data="two" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;height:400px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                         <el-table-column prop="comImage" align="center" :show-overflow-tooltip = "true">
                             <template slot-scope="scope">
                                  <img :src="scope.row.img" style="width: 50px;height:50px">
                             </template>
                        </el-table-column>
                        <el-table-column width="120" padding="0px">
                            <template slot-scope="scope">
                                <el-button @click="handleClick(scope.row)" type="text" size="small" icon="el-icon-edit" style="padding:0">编辑</el-button>
                                <el-button type="text" size="small" icon="el-icon-lx-delete" style="padding: 0">删除</el-button>
                                 <el-button type="text" size="small" icon="el-icon-lx-right" style="padding: 0;margin-left: 28px">下级</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="TwoTotalNum" :page-size="TwoPageSize" :current-page.sync="TwoCurrentPages"></el-pagination>
                    </div>
                </el-card>
            </el-col>




            <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>三级分类</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                     <el-table :data="three" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;height:400px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                         <el-table-column prop="comImage" align="center" :show-overflow-tooltip = "true">
                             <template slot-scope="scope">
                                  <img :src="scope.row.img" style="width: 50px;height:50px">
                             </template>
                        </el-table-column>
                        <el-table-column width="120" padding="0px">
                            <template slot-scope="scope">
                                <el-button @click="handleClick(scope.row)" type="text" size="small" icon="el-icon-edit" style="padding:0">编辑</el-button>
                                <el-button type="text" size="small" icon="el-icon-lx-delete" style="padding: 0;magin">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="ThreeTotalNum" :page-size="ThreePageSize" :current-page.sync="ThreeCurrentPages"></el-pagination>
                    </div>
                </el-card>
            </el-col>



         </el-row>
    </div>
</template>
<script>
    export default {
         data:function() {
            return {
                OneTotalNum : 1,// 总记录数
                OnePageSize : 5,// 一页显示记录数
                OnePage:1,//记录当前页数
                OneCurrentPages:0,//设置当前页数

                TwoTotalNum : 0,// 总记录数
                TwoPageSize : 5,// 一页显示记录数
                TwoPage:1,//记录当前页数
                TwoCurrentPages:0,//设置当前页数

                ThreeTotalNum : 0,// 总记录数
                ThreePageSize : 5,// 一页显示记录数
                ThreePage:1,//记录当前页数
                ThreeCurrentPages:0,//设置当前页数

                one: [
                    // {
                    //     title: '电脑',
                    //     img:require('../../../static/ManageImg/'+'DJDU'+'.png')
                    // },
                    // {
                    //     title: '手机',
                    //     img:require('../../../static/ManageImg/'+'DJDU'+'.png')
                    // },
                    // {
                    //     title: '维修',
                    //     img:require('../../../static/ManageImg/'+'DJDU'+'.png')
                    // }
                ],
                two: [],
                three: []                                   
            }
    },
    //页面加载函数
    mounted(){
        this.getOne();
    },
    methods: {
        getOne(){
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
                        if(this.items[i].grade == 0){
                            this.items[i].role = '超级管理员'
                        }
                        else if(this.items[i].grade == 1){
                            this.items[i].role = '购物管理员'
                        }
                        else{
                            this.items[i].role = '维修管理员'
                        }
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
        }
    }
}
</script>
<style>
.page{
    text-align: center;
    
}
</style>