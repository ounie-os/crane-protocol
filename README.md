# 用于jetlinks平台的协议包-塔吊-crane
### 一、通过HTTP拉取塔吊的信息

目前使用规则引擎去定时触发设备发送指令，利用消息拦截器，在其中发送http请求给塔吊，再根据塔吊回复的信息，刷新塔吊设备的属性信息。

### 二、接收HTTP的推送数据

通过decode来接收并解析数据