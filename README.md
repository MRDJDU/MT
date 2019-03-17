# MT维修小店

## 项目描述
- 基于微信小程序的小程序商城买家端，采用`wepy`框架开发；
- 项目实现了基本的商店功能，预约维修、在线指导、销售零件；

## 部署说明

### 安装环境
```
  # 小程序客户端
  ## 安装（更新） wepy 命令行工具。
  cnpm install wepy-cli -g
  ## 安装依赖包
  cnpm install
  ## 开发实时编译。
  npm run dev
```
### 小程序工具
- 使用微信开发者工具-->添加项目，选择dist目录；
- 可以使用体验APPID；
- 关闭ES6转ES5；
- 关闭上传代码时样式自动补；
- 关闭代码压缩上传；
- 打开不校验合法域名；

> [WePY开发文档参考](https://tencent.github.io/wepy/)

## 开发日志
- 2019.02.11
  - 初始化项目，编写第一个实体商品分类
  - 完善完善了CategoryDto的动态拼装查询条件
- 2019.02.12
  - 跨域问题解决，证书验证导致接口工具请求后台无法接收数据
  - 大更新，异常处理放在controller层，定义了封装的返回信息类，调整枚举类位置
  - 大更新，更改了spring的版本1.0到2.0，引入了几个包，定义封装了分页条件，修改了操作结果信息类，实现了接收json格式的多个对象参数
- 2019.02.13
  - 优化了处理前端请求操作结果信息封装类的数据，新增了错误信息字段，状态码改为boolean类型，更符合逻辑
  - controller调整为RESTFUL 标准，增加-post 删除和批量删除-delete 修改-put 查询-get
  - 实现了分类是否显示的批量操作以及优化了url访问路径
- 2019.02.21
  - 大更新，完成了数据库所有表的设计，新建好所有实体和其对应的关系
  - 数据库设计图
    ![](https://github.com/MRDJDU/MT/raw/master/ExplicateMTImg/数据库设计图.jpg)
- 2019.02.22
  - 更新，优化了数据库的设计，编写好ER图
  - MT_ER图 数据库设计
    ![](https://github.com/MRDJDU/MT/raw/master/ExplicateMTImg/MT_ER数据库设计.jpg)
- 2019.02.27
  -添加日志框架logback
- 2019.03.10
  -更新了前段页面，使用了vue+element搭建后台页面,目前写了登陆界面，开始前后台结合。
   前后台交互使用了axios技术，进行异步处理请求。
- 2019.03.11
  - 新增了管理员的新增和登陆的方法，管理员用户密码使用MD5进行加密
- 2019.03.12
  - 搭建起基础的仪表盘界面，用vue-router来控制用户登录权限的原理，应用了vue-router提供的router.beforeEach来注册一个全局钩子，用户只能通过登陆界面进入系统
  - 用户是否登陆使用localStorage存储用户id来判断
  - 仪表盘主页面使用bus总线进行组件间通讯
  - 界面展示
    ![](https://github.com/MRDJDU/MT/raw/master/ExplicateMTImg/仪表盘1.png)
    ![](https://github.com/MRDJDU/MT/raw/master/ExplicateMTImg/仪表盘2.png)
  - 完成了用户管理模块的界面设计，能够正常新增用户，具体细节明天完善
    ![](https://github.com/MRDJDU/MT/raw/master/ExplicateMTImg/用户管理.png)
- 2019.03.13
  - 实现了管理员分页查询，并动态加载组件，有一个问题未解决，图片问题没有显示
- 2019.03.18
  - 彻底完善管理员管理界面，以及进一步进行后台拦截权限设置