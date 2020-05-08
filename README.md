## spring-boot-shrio

无需连接数据库连接，可直接运行
角色权限代码在LoginServiceImpl中设置为死数据

|用户|角色|权限|
|-----|-----|-----|
|charles | admin| add,query|
|zhangsan|user|query|

### 模拟角色
* 无登陆状态下访问接口
http://localhost:9090/index
```
请重新登陆!
```
* qingyang登陆(使用浏览器测试)

http://localhost:9090/login?userName=charles&password=123456

```
login success
```

访问 http://localhost:9090/index
```
添加成功!
```

* zhangsan登陆
http://localhost:9090/login?userName=zhangsan&password=123456
```
login success
```
访问 http://localhost:9090/index
```
权限不足!
```
