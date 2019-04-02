<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
            text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.subs">
                            <el-submenu v-if="subItem.subs" :index="subItem.index" :key="subItem.index">
                                <template slot="title">{{ subItem.title }}</template>
                                <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">
                                    {{ threeItem.title }}
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item v-else :index="subItem.index" :key="subItem.index">
                                {{ subItem.title }}
                            </el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                // 任何权限都可进入
                items: [
                    {
                        icon: 'el-icon-lx-home',
                        index: 'index',
                        title: '系统首页'
                    },
                    {
                        icon: 'el-icon-lx-cascades',
                        index: 'category',
                        title: '分类管理'
                    },
                    {
                        icon: 'el-icon-lx-favor',
                        index: 'brand',
                        title: '品牌管理'
                    },
                    {
                        icon: 'el-icon-lx-read',
                        index: 'article',
                        title: '文章管理'
                    }
                ],
                // 超级管理员权限进入
                adminItems:[
                    {
                        icon: 'el-icon-lx-friend',
                        index: 'user',
                        title: '用户管理'
                    }
                ],
                // 购物管理员权限进入
                tradeItems:[
                    {
                        icon: 'el-icon-lx-goods',
                        index: 'goods',
                        title: '商品管理'
                    },
                    {
                        icon: 'el-icon-lx-tag',
                        index: 'orders',
                        title: '订单管理'
                    },
                     {
                        icon: 'el-icon-lx-comment',
                        index: 'evaluation',
                        title: '商品评价'
                    }
                ],
                // 维修管理员权限进入
                mtItems:[
                    {
                        icon: 'el-icon-lx-service',
                        index: 'maintain',
                        title: '服务管理'
                    },
                    {
                        icon: 'el-icon-lx-global',
                        index: 'subscribe',
                        title: '预约管理'
                    },
                    {
                        icon: 'el-icon-lx-message',
                        index: 'assess',
                        title: '维修评价'
                    }
                ]
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
        },
        // 权限管理
        mounted(){
            const role = sessionStorage.getItem('manage_grade');
            // 超级管理员
            if(role=="0"||role==0){
                this.adminItems.forEach(adminItem => {
                    this.items.push(adminItem);
                });
            }
            // 购物管理员
            if(role=="1"||role==1){
                this.tradeItems.forEach(tradeItem => {
                    this.items.push(tradeItem);
                });
            }
            // 维修管理员
            if(role=="2"||role==2){
                this.mtItems.forEach(mtItem => {
                    this.items.push(mtItem);
                });
            }
            // 开发者
            if(role=="3"||role==3){
                 this.tradeItems.forEach(tradeItem => {
                    this.items.push(tradeItem);
                });
                this.mtItems.forEach(mtItem => {
                    this.items.push(mtItem);
                });
                this.adminItems.forEach(adminItem => {
                    this.items.push(adminItem);
                });
            }
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom:0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar{
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 150px;
    }
    .sidebar > ul {
        height:100%;
    }
</style>
