<template>
    <div>
            <div class="crop-demo">
                <img :src="cropImg" class="pre-img">
                <div class="crop-demo-btn">选择图片
                    <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage"/>
                </div>
            </div>
        
            <el-dialog title="裁剪图片" :visible.sync="dialogVisible" width="30%">
                <vue-cropper ref='cropper' :src="imgSrc" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage" style="width:100%;height:100%;":aspectRatio="1"></vue-cropper>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="cancelCrop">取 消</el-button>
                    <el-button type="primary" @click="submitEidt">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import VueCropper  from 'vue-cropperjs';
    export default {
        data: function(){
            return {
                defaultSrc: require('../../assets/ManageImg/DJDU.png'),//开始默认图片路径
                imgSrc: '',
                cropImg: '',//动态选择图片后路径
                dialogVisible: false,
            }
        },
        components: {
            VueCropper
        },
        methods:{
            setImage(e){//点击选择图片按钮
                const file = e.target.files[0];
                if (!file.type.includes('image/')) {//判断是否为图片
                    return;
                }
                const reader = new FileReader();
                reader.onload = (event) => {
                    this.dialogVisible = true;
                    this.imgSrc = event.target.result;
                    this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
                };
                reader.readAsDataURL(file);
            },
            cropImage () {//展示上传后图片样式
                this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
            },
            cancelCrop(){//取消恢复开始图片
                this.dialogVisible = false;
                this.cropImg = this.defaultSrc;
            },
            submitEidt(){//点击确定后上传截取后的图片到服务器中
                // 保留this
                let _this=this;
                this.axios.post('http://localhost:8080/mt/manage/edit',{
                    "image":_this.cropImg
                })
                .then(response => {
                    // if(response.data.statuCode){
                    //     this.items=response.data.data.content;
                    //     this.totalNum=response.data.data.totalElements;
                    //     this.currentPages=1;
                    // }
                    // else{
                    //     this.$notify.error({
                    //         title: response.data.message,
                    //         offset: 100,
                    //         showClose: false
                    //     });
                    //     this.addManage = false;
                    //     _this.addForm.username=null;
                    // }
                });
                this.dialogVisible = false;
            }
        },
        created(){
           
            this.cropImg = this.defaultSrc;//页面创建时赋值默认路径
        }
    }
</script>

<style scoped>
    .pre-img{   
        width: 100px;
        height: 100px;
        /* background: #f8f8f8;
        border: 1px solid #eee; */
        border-radius: 50%;
    }
    .crop-demo{
        display: flex;
        align-items: flex-end;
    }
    .crop-demo-btn{
        position: relative;
        width: 100px;
        height: 40px;
        line-height: 40px;
        padding: 0 20px;
        margin-left: 30px;
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