```
1.执行docker-compose up 
2.进入mysql容器，创建db库
    create database nacosdb;
    use nacosdb;
    source /var/nacos-mysql.sql;
3.重启nacos-server容器
```