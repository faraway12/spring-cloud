# docker

![image-20210906221519792](/Users/lijialin/Library/Application Support/typora-user-images/image-20210906221519792.png)



## 安装Docker

```shell
# 安装所需软件包
$ yum install -y yum-utils
# 使用国内的源
$ yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
# 安装Docker CE
$ yum install docker-ce docker-ce-cli containerd.io
# 启动docker
$ systemctl start docker
# 修改docker镜像源
在 /etc/docker/daemon.json 中写入如下内容
{
  "registry-mirrors": [
    "https://hub-mirror.c.163.com",
    "https://mirror.baidubce.com"
  ]
}
```



## es

1. es很占内存，启动时需要进行内存限制

   ```shell
   docker run -d --name es -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms64m -Xmx512m" elasticsearch
   ```

## docker可视化

- portainer

  ```shell
  docker run -d -p 9000:9000 --restart=always -v /var/run/docker.sock:/var/run/docker.sock --privilrged=true portainer
  ```

- Rancher(CI/CD)

1. 什么是portainer？

   docker图形化界面管理工具，提供一个后台面板供我们操作

   一般不会使用，了解即可

## 制作镜像

- 镜像加载原理

  1. UinonFS(联合文件系统)

  2. bootfs和rootfs

  3. 对于一个精简的os，只需要提供一个rootfs

     Docker的 rootfs可以很小，只需要简单的工具和命令，底层直接使用宿主机的内核；另外也不需要再提供bootfs，所以docer镜像很小

- 分层原理 

  1. docker镜像都是只读的，当容器启动时，一个新的可写层被加载到镜像的顶部

- commit镜像

  ```shell
  docker commit -m="提交的描述信息" -a="作者" 容器id 目标镜像名:[TAG]
  ```
## 容器数据卷

- 什么是容器数据卷

  1. 数据存储在容器的话，如果删除容器那数据就会丢失

     ==需求：数据可以持久化==

  2. 容器之间可以有一个数据共享的技术，讲docker中产生的数据同步到本地，这就是卷技术

  3. 通过将容器内的目录==挂载==到本地上实现

- 使用数据卷

  1. 方式一：直接使用命令来挂载 -v
     ```shell
     docker run -it -v 主机目录:容器目录 镜像名
     ```
     
  2. 挂载mysql
     
     ```shell
     docker run -d -p 3306:3306 -v  /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=llll --name mysql00  mysql:5.7
     ```
  
- 具名和匿名挂载

  1. 匿名挂载： -v 容器内路径

     ```shell
     docker run -d -P --name nginx00 -v /etc/nginx nginx
     ```

     查看所有卷的情况

     ```shell
     docker volume ls
     ```

     "local   f17e46c7a3a812d2997a8c5cf2a07bd0c9114b400c93ca81937d9915e2eb10d7"

     发现上面这样的其实就是匿名挂载，我们在-v只写了容器内额路径，没写容器外的

  2. 具名挂载： -v 卷名:容器内路径

     ```shell
     docker run -d -P --name nginx01 -v juming-nginx:/etc/nginx nginx
     ```
     
     "local   juming-nginx"
     
  3. 所有没有指定本地主机地址挂载的容器卷，都是在'/var/lib/docker/volumes/卷名/_data'
  
- 区分指定路径挂载、匿名挂载、具名挂载
  
  ```shell
  -v 宿主机路径:容器内路径		#指定路径
  -v 容器内路径							#匿名
  -v 卷名:容器内路径					#具名
  ```
  
- 扩展

  ```shell
  # 通过  容器内路径:ro rw 改变读写权限
  ro	readonly #只读
  	-只能通过外部宿主机改变
  rw	readwrite #可读可写
  	-默认
  ```

## 数据卷容器

  - 实现两个容器之间数据同步

  - --volumes-from

    ```shell
    # 启动mysql00
    docker run -d -p 3306:3306 -v  /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=llll --name mysql00  mysql:5.7
    # 启动mysql01，挂载在mysql00下，实现两个数据同步
    docker run -d -p 3307:3306 -volumes-from mysql00 -e MYSQL_ROOT_PASSWORD=llll --name mysql00  mysql:5.7
    ```

    

## DockerFile

- 初识Dockerfile

  1. Dockerfile就是用来构建docker镜像的构建文件(命令脚本)

  2. 通过这个脚本可以生成镜像，镜像是一层一层的，脚本一个个命令，每个命令也就是一层

     ```shell
     # 准备dockerfile
     cat dockerfile1          
     FROM centos
     VOLUME ["volume01","volume02"]
     CMD echo "---end---"
     CMD /bin/bash
     # 执行构建
     docker build  -f dockerfile1 -t ljl/centos . 			#在当前目录生成ljl版本的centos
     ----------------
     Sending build context to Docker daemon  2.048kB
     Step 1/4 : FROM centos
      ---> 300e315adb2f
     Step 2/4 : VOLUME ["volume01","volume02"]
      ---> Running in 0057c13edda6
     Removing intermediate container 0057c13edda6
      ---> 6dc0d180050a
     Step 3/4 : CMD echo "---end---"
      ---> Running in 999276b3d394
     Removing intermediate container 999276b3d394
      ---> 978bb63ae724
     Step 4/4 : CMD /bin/bash
      ---> Running in d78ef706be21
     Removing intermediate container d78ef706be21
      ---> 2ae2d0bf4c2f
     Successfully built 2ae2d0bf4c2f
     Successfully tagged ljl/centos:latest
     ```
     
  3. 运行容器，发现在容器内有两个volume01、volume02

- 发布镜像

  1. DockerHub

  ```shell
  # 登录
  docker login -u name -p password
  # 打标签 [tag]为DockerHub上的仓库名
  docker tag [imageID] [tag]
  # 提交
  docker push [tag]
  ```
  
  2. 阿里云
     - 登录阿里云
     - 找到容器镜像服务
     - 创建命名空间
     - 创建容器镜像
  
  

## Docker网络(容器编排 集群部署)

- 理解docker0

  ```shell
  $ ip addr
  1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
      link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
      inet 127.0.0.1/8 scope host lo
         valid_lft forever preferred_lft forever
      inet6 ::1/128 scope host 
         valid_lft forever preferred_lft forever
  2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
      link/ether 52:54:00:ca:e7:8b brd ff:ff:ff:ff:ff:ff
      inet 172.21.0.2/20 brd 172.21.15.255 scope global eth0
         valid_lft forever preferred_lft forever
      inet6 fe80::5054:ff:feca:e78b/64 scope link 
         valid_lft forever preferred_lft forever
  3: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default 
      link/ether 02:42:55:3a:61:7f brd ff:ff:ff:ff:ff:ff
      inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
         valid_lft forever preferred_lft forever
      inet6 fe80::42:55ff:fe3a:617f/64 scope link 
         valid_lft forever preferred_lft forever
  ```

  1. 思考：docker是如何处理容器网络访问的？

     ```shell
     # 启动一个cnetos容器
     $ docker run -d -P --name centos00 centos
     #查看容器内部网络地址 ip addr
     $ docker exec -it centos00 ip addr
     1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
         link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
         inet 127.0.0.1/8 scope host lo
            valid_lft forever preferred_lft forever
     114: eth0@if115: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
         link/ether 02:42:ac:11:00:03 brd ff:ff:ff:ff:ff:ff link-netnsid 0
         inet 172.17.0.3/16 brd 172.17.255.255 scope global eth0
            valid_lft forever preferred_lft forever
      ## 这个eth0@if115是docker分配的
      # 宿主机能不能ping通容器内 可以ping通
      ping 172.17.0.3
     PING 172.17.0.3 (172.17.0.3) 56(84) bytes of data.
     64 bytes from 172.17.0.3: icmp_seq=1 ttl=64 time=0.060 ms
     64 bytes from 172.17.0.3: icmp_seq=2 ttl=64 time=0.040 ms
     # 容器之间也是可以相互ping通的
     $ docker exec -it centos00 ping 172.17.0.2
     # 当然，容器ping宿主机的docker0和eth0也是可以通的
     ```

     原理：

     - 我们每启动一个docker容器，docker就会给容器分配一个ip，我们只要安装了docker，就会有一个网卡docker0
     - docker0是一个虚拟网卡，采用桥接模式
     - evth-pair技术

       1. 每启动一个容器，就会出现一对网卡
       2. 我们发现这个日期带来的网卡都是一对一对的
       3. evth-pair就是一对的虚部设备接口，他们都是成对出现的，一端连着协议，一端彼此相连
       4. 这是因为有这个特性，evth-pair充当一个桥梁，连接各种虚拟网络设备
       5. Openstac,Docker容器之间的连接，OVS的连接，都是使用evth-pair技术

     <img src="/Users/lijialin/Library/Application Support/typora-user-images/image-20210905165225395.png" alt="image-20210905165225395"  />

       6. 结论：两个容器共用一个路由器，也就是docker0

          所有的容器在不指定网络的情况下，都是docker0路由的，docker会给容器分配一个默认的ip，大约是2的16次方个(65535)

     		7. 容器停止虚拟网卡对就消失了

- --link

  1. 思考一个场景，我们编写了一个微服务，database url = ip;项目不重启，数据库ip换了，我们希望可以处理这个问题，能通过名字来进行访问容器吗？

  2. 使用容器连接技术

     ```shell
     $ docker run -it --name centos01 centos
     $ docker run -it --name centos02 --link centos01  centos ## --link连接容器1
     $ docker exec -it centos02 ping centos01               ## 容器2可以通过容器1的名字ping通
     PING centos01 (172.17.0.2) 56(84) bytes of data.
     64 bytes from centos01 (172.17.0.2): icmp_seq=1 ttl=64 time=0.076 ms
     ```

  3. 使用inspect命令查看cnetos02，可以看到HostConfig的links配置了连接到centos01
  
  4. 或者直接查看centos02的/etc/hosts文件
  
     ```shell
     $ docker exec -it centos02 cat /etc/hosts
     127.0.0.1	localhost
     ::1	localhost ip6-localhost ip6-loopback
     fe00::0	ip6-localnet
     ff00::0	ip6-mcastprefix
     ff02::1	ip6-allnodes
     ff02::2	ip6-allrouters
     172.17.0.2	centos01 9d383d261267 ## 在hosts中增加了centos01的映射
     172.17.0.3	795e7282a81d
     ```
  
  
  5. 其实不再建议使用--link命令了，一般使用自定义网络，因为docker0存在不支持容器名连接访问的问题
  
- 自定义网络

  1. 查看所有的网络

     ```shell
     $ docker network ls
     NETWORK ID     NAME      DRIVER    SCOPE
     20743d9d851e   bridge    bridge    local  # docker0
     2f7e0b861963   host      host      local
     8ba245545a87   none      null      local
     ```

  2. 网络模式

     - bridge：桥接模式 （docker默认）事实上，我们之前启动容器，默认是有一个--net bridge启动参数的

     - none：不配置网络

     - host：主机模式，和宿主机共享网络

     - container：容器内网络连通（用的少）

  3. 创建自定义网络
  
     ```shell
     # 默认使用docker0
     # docker0特点：默认，域名不能访问，--link可以打通连接
     $ docker run -it --name centos01 --net bridge centos
     
     # 自定义一个网络 通常也使用bridge模式
     $ docker network create --driver bridge --subnet 192.168.0.0/16 --gateway 192.168.0.1 mynet
     # 启动容器
     $ docker run -it -P  --name centos01 --net mynet centos
     # 查看自定义网络信息
     $ docker network inspect mynet
     # 直接用centos02去ping容器centos01的名字，发现可以直接ping通，这就是对比docker0的好处，不需要再使用--link
     $ docker exec -it centos02 ping centos01 
     PING centos01 (192.168.0.2) 56(84) bytes of data.
     64 bytes from centos01.mynet (192.168.0.2): icmp_seq=1 ttl=64 time=0.052 ms
     ```
  
  4. 我们自定义的网络docker都帮我们维护好了对应的关系，推荐使用这样使用网络

	5. 在集群中，建议不同集群配置在不同自定义网络下
	
- 网络连通

  ```shell
  # 创建两个网络
  $ docker network create --driver bridge --subnet 192.167.0.0/16 --gateway 192.167.0.1 mynet01
  $ docker network create --driver bridge --subnet 192.168.0.0/16 --gateway 192.168.0.1 mynet02
  # 启动容器
  $ docker run -it -P  --name centos01 --net mynet01 centos
  $ docker run -it -P  --name centos02 --net mynet01 centos
  $ docker run -d -P  --name nginx01 --net mynet02 nginx
  $ docker run -d -P  --name nginx02 --net mynet02 nginx
  ```

  1. 同一个网段下是可以ping通的，但是不同网段ping不同，那如何实现centos01去ping通nginx01？

  2. 不能将两个容器直接相连，我们要做的应该是将mynet02网络连通到centos01容器中

  3. $ docker network connect [OPTIONS] NETWORK CONTAINER

     ```shell
     # 测试打通mynet02连通到centos01中
     $ docker network connect mynet02 centos01
     # 查看mynet02发现连通之后将centos01加入到了该网络
     docker network inspect mynet01
     ```

- 部署redis集群

  ![image-20210905211914846](/Users/lijialin/Library/Application Support/typora-user-images/image-20210905211914846.png)

  1. 创建redis网络

     ```shell
     $ docker network create redis --subnet 192.168.0.0/16
     ```

  2. 编写redis配置文件

     ```shell
     $ cat dockernet.sh 
     for port in $(seq 1 6);
     do
     mkdir -p /mydata/redis/node-${port}/conf
     touch /mydata/redis/node-${port}/conf/redis.conf
     cat << EOF > /mydata/redis/node-${port}/conf/redis.conf
     port 6379
     bind 0.0.0.0
     cluster-enabled yes 
     cluster-config-file nodes.conf
     cluster-node-timeout 5000
     cluster-announce-ip 192.168.0.1${port}
     cluster-announce-port 6379
     cluster-announce-bus-port 16379
     appendonly yes
     EOF
     done
     ```

  3. 启动容器

     ```shell
     $ cat dockernetrun.sh 
     for port in $(seq 1 6);
     do
     docker run -p 637${port}:6379 -p 1637${port}:16379 --name redis-${port} -v /mydata/redis/node-${port}/data:/data -v /mydata/redis/node-${port}/conf/redis.conf:/etc/redis/redis.conf -d --net redis --ip 192.168.0.1${port} redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
     done
     ```

  4. 进入容器部署集群

     ```shell
     $ docker exec -it redis-1 sh
     $ redis-cli --cluster create 192.168.0.11:6379 192.168.0.12:6379 192.168.0.13:6379 192.168.0.14:6379 192.168.0.15:6379 192.168.0.16:6379 --cluster-replicas 1
     ```

  5. 查看redis集群

     ```shell
     # redis客户端
     $ redis-cli -c
     # 查看redis节点
     127.0.0.1:6379> cluster nodes
     1fef85487e6846a974ea59d11a9fb9f568354eb5 192.168.0.12:6379@16379 master - 0 1630851541782 2 connected 5461-10922
     83dd1066d2f8a276c95212688eaf1b1caaa75308 192.168.0.14:6379@16379 slave ba58c7e6591f251858bf5ae8794e2997337ed5f8 0 1630851540580 4 connected
     2a37b392f05dd55178d6e83dcfab2ac1c8c33b1e 192.168.0.15:6379@16379 slave d230ea9bafbff9ce94b198ca556aab2937469de5 0 1630851540000 5 connected
     d230ea9bafbff9ce94b198ca556aab2937469de5 192.168.0.11:6379@16379 myself,master - 0 1630851541000 1 connected 0-5460
     ba58c7e6591f251858bf5ae8794e2997337ed5f8 192.168.0.13:6379@16379 master - 0 1630851540780 3 connected 10923-16383
     3f794486980d2f67edd9720647c6cbe32b36ea75 192.168.0.16:6379@16379 slave 1fef85487e6846a974ea59d11a9fb9f568354eb5 0 1630851540000 6 connected
     # set一个键值
     127.0.0.1:6379> set a b
     -> Redirected to slot [7365] located at 192.168.0.13:6379
     OK
     # 把redis-3主机停止 再get值
     192.168.0.13:6379> get a
     # redis-4作为redis-13的备机 会顶上
     192.168.0.14:6379> "b"
     ```

- SpringBoot微服务打包docker镜像

  1. 构建springboot项目
  2. 打包应用
  3. 编写Dockerfile
  4. 上传Dockerfile和jar包到服务器
  5. 构建镜像
  6. 发布运行

## 企业实战

### Docker Compose

- 简介

  1. Dockerfile build run 手动操作，单个容器

  2. 微服务。100个微服务，并且容器间还可能有依赖关系

  3. 使用Docker Compose来轻松高效管理容器，定义多个容器

     ---定义、运行多个容器

     ---YAML file 配置文件

     Compose is a tool for defining and running multi-container Docker applications. With Compose, you use a YAML file to configure your application’s services. Then, with a single command, you create and start all the services from your configuration. To learn more about all the features of Compose, see [the list of features](https://docs.docker.com/compose/#features).

     ---所有环境都可以使用Docker Compose

     Compose works in all environments: production, staging, development, testing, as well as CI workflows. You can learn more about each case in [Common Use Cases](https://docs.docker.com/compose/#common-use-cases).

     ---三步骤：

     Using Compose is basically a three-step process:

     1. Define your app’s environment with a ==Dockerfile== so it can be reproduced anywhere.
     2. Define the services that make up your app in ==docker-compose.yml== so they can be run together in an isolated environment.
     3. Run ==docker compose up== and the [Docker compose command](https://docs.docker.com/compose/cli-command/) starts and runs your entire app. You can alternatively run `docker-compose up` using the docker-compose binary.

  4. 批量容器编排

- 理解

  1. Compose是Docker官方的开源项目，需要安装。
  2. Dockerfile让程序在任何一个地方运行。web服务、redis、mysql、nginx一个个运行起码得run四次
  3. Docker Compose是单机部署，Docker Swarm是集群

- 安装

  ```shell
  curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.5/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
  chmod +x docker-compose
  ```

- 体验

    参考官方文档：https://docs.docker.com/compose/gettingstarted/

### Docker Swarm

### CI/CD Jenkins流水线

### k8s

