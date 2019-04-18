
<div style="margin-top: 30px;" id="s6" hidden>
    <el-table :data="SKU" class="tb-edit" style="width:100%" highlight-current-row>
        <el-table-column prop="name" label="属性名"></el-table-column>
        <el-table-column  label="属性值">
            <template slot-scope="scope">
                <el-input size="small" v-model="scope.row.value" placeholder="请输入内容"></el-input> <span>{{scope.row.value}}</span>
            </template>
        </el-table-column>
    </el-table>
</div>










//获取库存计量属性列表
this.axios.post('http://localhost:8080/mt/BaseSKU/getBaseSKUPage',{
    "baseSKUDto":{
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
        this.SKU=response.data.data.content;
        for(var i=0;i<this.SKU.length;i++){
            this.SKU.value=''
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