docker run -it --name nacos -p 8848:8848 -e PREFER_HOST_MODE=hostname -e MODE=standalone nacos/nacos-server:latest-slim
# 默认用户密码：nacos
# http://localhost:8848/nacos/