# common-sso
#### 实现基于oauth2.0+springboot+jwt实现的认证系统

##### 接入方式
- 跳转登录页面

/sso/page/login?redirectUrl=&appId=

请求方式:get

redirectUrl:客户端回调接口，用于接收授权码

appId:客户端向验证系统注册时分配的应用id，用于确认客户端身份

- 根据授权码和app信息获得jwt令牌

/sso/getToken

请求方式:post

请求参数:

appId:

appSecret:

code:

