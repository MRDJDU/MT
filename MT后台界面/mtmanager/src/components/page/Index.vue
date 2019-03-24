<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20">
                    <div style="height:120px">
                        <div class="user-info">
                            <img src="../../assets/ManageImg/DJDU.png" class="user-avator">
                            <div class="user-info-cont">
                                <div class="user-info-name">{{name}}</div>
                                <div>2019/03/20 12:06:54</div>
                                <div>{{role}}</div>
                            </div>
                        </div>
                        <div class="edit">
                                <el-row>
                                    <el-tooltip effect="dark" :content="fullscreen=` 修改信息 `" placement="left">
                                            <el-button icon="el-icon-edit" circle size='mini' ></el-button>
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
                        <span>维修知识</span>
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
                ]             
            }
        },
        computed: {
            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
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
</style>
