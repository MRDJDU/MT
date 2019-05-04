
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






module.exports = {

// 项目部署的基础路径
// 我们默认假设你的应用将会部署在域名的根部，
// 比如 https://www.my-app.com/
// 如果你的应用时部署在一个子路径下，那么你需要在这里
// 指定子路径。比如，如果你的应用部署在
// https://www.foobar.com/my-app/
// 那么将这个值改为 `/my-app/`
// baseUrl已经不支持使用，支持使用 publicPath
publicPath: '/',
// 将构建好的文件输出到哪里
outputDir: 'dist',
// 是否在保存的时候使用 `eslint-loader` 进行检查。
// 有效的值：`ture` | `false` | `"error"`
// 当设置为 `"error"` 时，检查出的错误会触发编译失败。
// 设置为 fasle 关闭esline规范
lintOnSave: false,
// 使用带有浏览器内编译器的完整构建版本
// 查阅 https://cn.vuejs.org/v2/guide/installation.html#运行时-编译器-vs-只包含运行时
// compiler: false,
runtimeCompiler: true, //关键点在这
// 调整内部的 webpack 配置。
// 查阅 https://github.com/vuejs/vue-doc-zh-cn/vue-cli/webpack.md
chainWebpack: () => {},
configureWebpack: () => {},
// vue-loader 选项
// 查阅 https://vue-loader.vuejs.org/zh-cn/options.html
// vueLoader: {},
// 是否为生产环境构建生成 source map？
productionSourceMap: true,
// CSS 相关选项
css: {
// 将组件内的 CSS 提取到一个单独的 CSS 文件 (只用在生产环境中)
extract: true,
// 是否开启 CSS source map？
sourceMap: false,
// 为预处理器的 loader 传递自定义选项。比如传递给
// sass-loader 时，使用 `{ sass: { ... } }`。
loaderOptions: {},
// 为所有的 CSS 及其预处理文件开启 CSS Modules。
// 这个选项不会影响 `*.vue` 文件。
modules: false
},
// 在生产环境下为 Babel 和 TypeScript 使用 `thread-loader`
// 在多核机器下会默认开启。
parallel: require('os').cpus().length > 1,
// 是否使用 `autoDLLPlugin` 分割供应的包？
// 也可以是一个在 DLL 包中引入的依赖的显性的数组。
// 查阅 https://github.com/vuejs/vue-doc-zh-cn/vue-cli/cli-service.md#dll-模式
// dll: false,
// PWA 插件的选项。
// 查阅 https://github.com/vuejs/vue-doc-zh-cn/vue-cli-plugin-pwa/README.md
pwa: {},
// 配置 webpack-dev-server 行为。
devServer: {
open: process.platform === 'darwin',
host: 'localhost',
disableHostCheck:true,
port: 8082,
https: false,
hotOnly: false,
// 查阅 https://github.com/vuejs/vue-doc-zh-cn/vue-cli/cli-service.md#配置代理
proxy: {
'/knbolg': {
target: 'http://localhost:8080/knbolg/',//设置你调用的接口域名和端口号 别忘了加http
changeOrigin: true,//如果需要跨域
pathRewrite: {
'^/knbolg': ''
//这里理解成用面的地址，
// 后面组件中我们掉接口时直接用api代替 比如我要调
// 用'http://425.0.100.100:8888/user/add'，直接写‘/api/user/add’即可
}
}
}, // string | Object
before: app => {
// `app` 是一个 expres