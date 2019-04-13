<template>
    <div>
            <el-button icon="el-icon-search" @click="eidt" circle></el-button>
            <el-dialog title="修改信息" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="editManage" width="40%" :show-close="false" center>
                <div class="crop-demo">
                    <img :src="cropImg" class="pre-img">
                    <div class="crop-demo-btn">选择图片
                        <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage"/>
                    </div>
                </div>
                <div style="margin: 0px 0px 10px 0px;text-align: center;" >
                    <el-checkbox-group v-model="seachCheckboxGroup" size="small" @change="changes"> 
                        <el-checkbox-button v-for="term in terms" :label="term" :disabled="checkboxName" :key="term">{{term}}</el-checkbox-button>
                    </el-checkbox-group>
                </div>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="editManageCancel('editForm')">取 消</el-button>
                    <el-button type="primary" @click="editSubmit('editForm')" :disabled=editdisable>确 定</el-button>
                </div>
            </el-dialog>
            <el-dialog title="裁剪图片" :show-close="false" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="dialogVisible" width="30%">
                <vue-cropper ref='cropper' :src="imgSrc" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage" style="width:100%;height:100%;":aspectRatio="1"></vue-cropper>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="cancelCrop">取 消</el-button>
                    <el-button type="primary" @click="submitEidt">确 定</el-button>
                </span>
            </el-dialog>
    </div>
</template>

<script>
    import VueCropper  from 'vue-cropperjs';
    export default {
        data: function(){
            return {
                defaultSrc: require('../../../static/ManageImg/'+sessionStorage.getItem('manage_img')+'.png'),//默认图片路径
                imgSrc: '',// 截取图片转化为的base64
                cropImg: '',// 显示图片的路径
                dialogVisible: false,// 截取图片的弹出框显示标志量
            }
        },
        components: {
            VueCropper
        },
        created(){
            this.cropImg = this.defaultSrc;//页面创建时赋值默认路径
        },
        methods:{
            //点击修改信息按钮
            eidt(){
                this.editManage=true;
            },
            //点击选择图片按钮
            setImage(e){
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
            //展示截取后图片样式
            cropImage () {
                this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
            },
            //取消恢复原图片
            cancelCrop(){
                this.dialogVisible = false;
                this.cropImg = this.defaultSrc;
                this.upimg = false;
            }
        }
    }
</script>

<style scoped>
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
        margin-left: 40%;
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