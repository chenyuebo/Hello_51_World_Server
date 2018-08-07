**支付技术部比赛——Hello 51 World：移动支付客户端组测试服务器**

**Tomcat server.xml设置**
```
<Connector port="8080" protocol="HTTP/1.1"
   connectionTimeout="20000"
   maxThreads="600"
   maxConnections="600"
   acceptCount="600"
   redirectPort="8443" />
```

**现支持如下情况的模拟测试**
1. 接口限流403
2. 参数校验401
3. 重复请求400
4. 处理成功200
5. 系统繁忙500
6. 自定义接口处理时间，模拟网络延时