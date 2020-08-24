## 图书馆管理系统
具有登录、权限校验，图书借阅，归还，增加图书等图书管理功能
整体上，用的是spring boot + mybatis框架。
数据库上，使用spring boot的默认数据库连接池管理工具Hikari，使用mybatis plus统一生成实体对象，mapper，service等代码，简化开发工作。
缓存上，使用spring boot默认提供的lettuce客户端，由于时间有限，仅在通过用户名查询用户信息时使用。
登录、权限管理上，使用spring security框架实现。
配置分离，打包后在启动参数中指定参数可以使不同环境配置生效。

### 权限管理
权限管理使用的spring security安全框架，使用了5张表存储用户、权限相关信息，其中permission表存放对应的url，
role表记录系统当前所拥有的角色信息，user记录每个用户信息，permission_role表记录权限与角色对应关系，role_user表记录用户与角色的对应关系。
permission中的权限授权给角色，角色赋予给对应的用户，由此用户获得对应的权限。

LibBookController下的接口为了方便测试，去掉了权限和登录校验，可以直接调用。

默认用户信息

用户名  | 密码 
----- | --- 
admin  | admin
abel  | abel

admin用户可访问全部接口

abel用户访问不了如下接口
http://localhost:8081/admin/home


### swagger 
图书信息相关接口接入了swagger配置，登录即可查看

http://localhost:8081/swagger-ui.html

### 图书管理
图书管理这块有三张表，lib_book_type表记录图书类型信息，lib_book表记录图书信息，其中包括书名，作者，库存等信息，lib_book_borrow表记录借阅信息，借出、归还状态通过字段区分。
重点讲讲借阅过程，考虑到一个人可以借同本书多次，借阅表没有在这块使用唯一性索引，所以要考虑并发插入时，数据唯一性。这块使用了redis实现轻量化的分布式锁，避免上述问题。借阅方法操作了多张表，所以使用了mysql事务处理，使用的是mysql默认的隔离级别，可重复读。因为事务级别不是串行化的，单单使用事务不能解决上面的并发问题，所以使用了轻量化的分布式锁。
归还过程与借阅类似。