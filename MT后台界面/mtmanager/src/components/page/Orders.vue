<template>
     <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-cascader expand-trigger="hover" :clearable = 'true' :options="options" class="handle-select mr10" v-model="selectedOptions"  placeholder="订单状态"></el-cascader>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" >

                <el-table-column prop="creatTime" label="下单日期" sortable width="200">
                </el-table-column>

                <el-table-column prop="name" label="用户名" width="150">
                </el-table-column>

                <el-table-column prop="address_xq" label="地址" width="200">
                </el-table-column>

                 <el-table-column prop="tel" label="电话" width="150">
                </el-table-column>
                
                <el-table-column prop="priceCount" label="总额">
                </el-table-column>

                 <el-table-column prop="stateName" label="状态">
                </el-table-column>

                <el-table-column label="操作" width="200" align="center">
                    <template slot-scope="scope">
                        <el-button type="warning" @click="out(scope.row.orders_id)">发货</el-button>
                        <el-button type="warning" @click="showD(scope.row.showDetitel)">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="total" :page-size="limit" center>
                </el-pagination>
            </div>
        </div>

         <!-- 详情弹出框 -->
        <el-dialog title="订单详情" :visible.sync="addVisible" width="70%">
           <el-table :data="showDetitel" border class="table" ref="multipleTable">

                <el-table-column prop="name" label="名称" sortable width="">
                </el-table-column>

                <el-table-column prop="num" label="数量" width="">
                </el-table-column>

                <el-table-column prop="price" label="单价" width="">
                </el-table-column>

                <el-table-column prop="zprice" label="总价" width="">
                </el-table-column>

                <el-table-column prop="photo_x" label="图片" width="">
                     <template slot-scope="scope">
                        <img :src="scope.row.photo_x" class="table_img">
                    </template>
                </el-table-column>

            </el-table>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">关 闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data:function(){
            return {
                total:1,
                page:1,
                limit:8,
                sort:"creatTime",
                dir:"DESC",
                options: [{
                    value: '2',
                    label: '未发货'
                    }, {
                    value: '3',
                    label: '已发货'
                    }, {
                    value: '4',
                    label: '完成'
                    }
                ],
                selectedOptions:[],
                tableData:[],
                showDetitel:[],
                addVisible:false,
                satae:0
            }
        },
        mounted(){
            this.getData();
        },
        methods: {
            getData(){
                // 保留this
                let _this=this;
                this.axios.post('mt/order/pcShow',{
                    "searchOrdersDto":{
                        "state":this.satae
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
                        this.tableData = [];
                        console.log(response.data.data);
                        for(var i = 0 ; i < response.data.data.content.length;i++){
                            if(response.data.data.content[i].orders.state == 2){
                                response.data.data.content[i].orders.stateName = '未发货';
                            }
                            if(response.data.data.content[i].orders.state == 3){
                                response.data.data.content[i].orders.stateName = '已发货';
                            }
                            if(response.data.data.content[i].orders.state == 4){
                                response.data.data.content[i].orders.stateName = '已完成';
                            }
                            response.data.data.content[i].orders.showDetitel = response.data.data.content[i].orderDetailsList;
                            this.tableData.push(response.data.data.content[i].orders);
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
                this.getData();
            },
           showD(e){
               this.showDetitel = e;
               this.addVisible = true;
           },
           search(){
               this.satae = this.selectedOptions[0];
               this.getData();
           },
           out(e){
               // 保留this
                let _this=this;
                this.axios.post('mt/order/changeorder',{
                        "orders_id": e,
                        "state":3
                })
                .then(response => {
                    if(response.data.statuCode){
                      this.getData();
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
    .table_img{
        width: 40px;
        height: 40px;
    }
</style>