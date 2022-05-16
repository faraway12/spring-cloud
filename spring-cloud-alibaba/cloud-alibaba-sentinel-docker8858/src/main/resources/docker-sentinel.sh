docker run -d --name sentinel-dashboard -p 8858:8858 bladex/sentinel-dashboard
# 默认用户密码：sentinel

#macos 使用docker失败
#解决办法：
https://gitee.com/rmlb/Sentinel/tree/master/sentinel-dashboard
git clone https://gitee.com/rmlb/Sentinel.git
mvn clean package 打一个jar包
java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=sentinel-dashboard -jar target/sentinel-dashboard.jar
--待后续自己制作一个docker镜像

--已制作完成:
docker build -t faraway12/sentinel-dashboard:latest .
docker push faraway12/sentinel-dashboard:latest
docker run -d --name sentinel-dashboard -p 8858:8858 faraway12/sentinel-dashboard

--自己制作的docker镜像好像也不行 目前看macos只能在本地运行了 暂不使用docker