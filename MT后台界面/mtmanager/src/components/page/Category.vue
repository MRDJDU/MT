<template>
    <div>
         <el-row :gutter="20">
             <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>一级分类</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                     <el-table :data="one" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.img}}</div>
                            </template>
                        </el-table-column>
                        <!-- <el-table-column prop="comImage" label="公司Logo" align="center" width="150px" padding="0px"
                        :show-overflow-tooltip = "true">
                             <template slot-scope="scope">
                                  <img :src="api + scope.row.comImage" alt="" style="width: 36px;height:36px">
                             </template>
                          </el-table-column> -->
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="totalNum" :page-size="pageSize" :current-page.sync="currentPages"></el-pagination>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>二级分类</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                     <el-table :data="two" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="totalNum" :page-size="pageSize" :current-page.sync="currentPages"></el-pagination>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card shadow="hover">
                   <div slot="header" class="clearfix">
                        <span>三级分类</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                     <el-table :data="three" :show-header="false" style="width: 100%;font-size:14px; margin-bottom: 20px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="page">
                        <el-pagination background layout="prev, pager, next" :total="totalNum" :page-size="pageSize" :current-page.sync="currentPages"></el-pagination>
                    </div>
                </el-card>
            </el-col>
         </el-row>
    </div>
</template>
<script>
    export default {
         data() {
            return {
                totalNum : 20,// 总记录数
                pageSize : 8,// 一页显示记录数
                page:1,//记录当前页数
                currentPages:0,//设置当前页数
                name: 'DJDU',
                one: [{
                        title: '今天要修复100个bug',
                        img:'123',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要写100行',
                        status: false,
                    }, {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: true,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: false,
                    }
                ]             
            }
    }
    }
</script>
<style>

</style>