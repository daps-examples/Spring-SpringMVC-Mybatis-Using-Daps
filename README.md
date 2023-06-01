> 整合Spring-SpringMVC-Mybatis-Maven框架，实现简单的登录DEMO [项目来源](https://github.com/bestwaiting/Spring-SpringMVC-Mybatis.git)

## 验证过程
### 样例项目
#### 初始化
1. SQL 初始化。执行 `ssm.sql`
2. 修改数据库链接信息。`src/main/resources/config.properties`
#### 打包
执行一下命令，得到 `ssm-0.0.3.war`
```maven
mvn clean package -Dmaven.test.skip=true -U 
```
### 使用 BES 部署
#### 安装 BES
1. `./BES091000B5213-SUSE64-X86-V9.bin -i console`

[安装日志](doc-attachment/iastool.log)
#### 部署
1. 创建 BES 应用环境
```shell
/home/ubuntu/data/bes/BES910/bin/iastool create --domain --passport admin --user admin --adminport 1900 domain3
/home/ubuntu/data/bes/BES910/bin/iastool start --domain --passport admin domain3
/home/ubuntu/data/bes/BES910/bin/iastool create --node --passport admin --domainname domain3 Node3
/home/ubuntu/data/bes/BES910/bin/iastool start --node --passport admin --domainname domain3 Node3
/home/ubuntu/data/bes/BES910/bin/iastool create --instance --passport admin --node Node3 Server3
/home/ubuntu/data/bes/BES910/bin/iastool start --instance --passport admin Server3
/home/ubuntu/data/bes/BES910/bin/iastool create --cluster --passport admin --user admin Cluster3
/home/ubuntu/data/bes/BES910/bin/iastool create --instance --passport admin --node Node3 --cluster Cluster3 cls3
```
2. 部署
```shell
/home/ubuntu/data/bes/BES910/bin/iastool deploy --passport admin --host localhost --port 1900 --user admin --target Server3 /home/ubuntu/apps/ssm-0.0.3.war
```
3. 日志
   1. `tail -f /home/ubuntu/data/bes/BES910/var/domains/domain3/nodes/Node3/manager/logs/server.log`==>[server.log](doc-attachment/server.log)
   2. `tail -f /home/ubuntu/data/bes/BES910/var/domains/domain3/nodes/Node3/instances/Server3/work/log4j.log`==>[应用日志](doc-attachment/log4j.log)
