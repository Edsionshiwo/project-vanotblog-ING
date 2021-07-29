# 前端部分 Vue

---

<p align="center">
</p>
<p align="center">
  <a href="https://github.com/vuejs/vue">
    <img src="https://img.shields.io/badge/vue-2.6.11-brightgreen.svg" alt="vue"/>
  </a>
</p>


## 介绍
>一个基于 Vue 开发的简单博客项目。目前为 Demo 版本。
## 开发工具与环境

- WebStorm 2021.1
- Node.js v14.16.0
- vue 2.6.11

## 建立本地项目

在完成了上述开发工具与环境的安装与配置以后。

### 建立本地 Vue 项目

#### 打开项目

WebStorm 打开 git 仓库目录下 vue 文件夹即可。

#### 安装依赖

Terminal 终端控制台下。

```
npm install
```

## 启动项目
```
npm run serve
```

## 编译打包

该操作在开发阶段不涉及。

```
npm run build
```

## 试试看吧

在浏览器中。

```
http://localhost:8080
```

## 全栈开发须知

该前端项目中通过 `Mock.js` 来拦截前端向后端的请求并返回模拟数据。

若要进行完整的前后端开发获取真实的后端数据。

### 关闭 Mock 模拟

在 src 目录下 `main.js` 。

```js
...
require('@/Mock')			// <--------- 注释该行
...
```

### 开启全局请求 BaseURL

在项目根目录下 `.env.development` 。

```
...
# VUE_APP_BASE_API = 'http://localhost/'       # <---------- 启用该行
...
```

