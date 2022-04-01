```
1.执行mysql-docker-compose up 
    docker-compose -f mysql-docker-compose up
2.进入mysql容器，创建db库
    create database nacosdb;
    use nacosdb;
    source /var/nacos-mysql.sql;
```


docker run -it -e MODE=standalone -e SPRING_DATASOURCE_PLATFORM=mysql -e MYSQL_SERVICE_HOST=127.0.0.1 -e MYSQL_SERVICE_PORT=3306 -e MYSQL_SERVICE_USER=root -e MYSQL_SERVICE_PASSWORD=123456 -e MYSQL_SERVICE_DB_NAME=nacosdb -p 8848:8848 --restart=always --name nacos bash