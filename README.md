[![Travis](https://img.shields.io/travis/rust-lang/rust.svg)]()
[![Jenkins tests](https://img.shields.io/jenkins/t/https/jenkins.qa.ubuntu.com/view/Precise/view/All%20Precise/job/precise-desktop-amd64_default.svg)]()
[![Dockbit](https://img.shields.io/dockbit/DockbitStatus/health.svg?token=TvavttxFHJ4qhnKstDxrvBXM)]()
[![Bower](https://img.shields.io/bower/v/bootstrap.svg)]()
[![Packagist](https://img.shields.io/packagist/l/doctrine/orm.svg)]()

# Change Log
<hr>

## v0.1.0 (2017/10/15)
* 歌曲检索
* 歌手检索
* 专辑检索
* 用户检索
* 对返回的 json 进行解析获取真正的歌曲 URL 地址
* 基本的一个前端页面
* 使用 Vue 请求后台数据

# Install 
1. clone 代码到本地
2. 打开 修改application-dev.yml 为 application.yml 配置数据库以及启动端口
3. 安装 maven
4. 使用 maven 对项目进行编译，会产生一个快照版本的 jar 包
5. 使用 java -jar projectName.jar 运行项目
6. 在浏览器中访问 127.0.0.1:port 即可