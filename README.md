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
- 2019.02.11：初始化项目，编写第一个实体商品分类；
     完善完善了CategoryDto的动态拼装查询条件
