"# J2EEStudy" 

# 编码问题
- tomcat server.xml    URIEncoding="UTF-8"
- tomcat -vm op     -Dfile.encoding=UTF-8

### 谷歌浏览器preview乱码问题
- response.setContentType("application/json");

#### alert框
#### 表单查询

https://blog.csdn.net/iteye_14864/article/details/81928287
#### 集群中的会话共享
- 1.ip_hash nginx 配置轮询
- 2.session 同步 
  - 应用服务器中配置
  - web.xml中配置分布式标签
- 3.session 共享
  - seesion统一放redis中
- 4. 认证中心
#### 亮点
- J2EE
  - 单点登录
    - 登录->校验->登录成功->设置cookie
    - 请求需要登陆的接口->过滤器->校验cookie->
  - 过滤器
- JDBC查询类
- 统一返回处理
- 自定义异常处理
